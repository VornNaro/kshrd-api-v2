package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryFilter;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class EventCategoryProvider {

    //TODO : Select all event categories with pagination =========================================================
    public String selectWithPagination(@Param("name")EventCategoryFilter name,
                             @Param("page")Pagination pagination){
        return new SQL(){{

            SELECT("*");
            FROM("hrd_event_category");
            WHERE("status = true");
            ORDER_BY("hrd_event_category.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
            if (name.getName()!= null){
                WHERE("hrd_event_category.name ILIKE '%'||#{name.name}||'%'");
            }
        }}.toString();
    }
    //TODO: Get partner type by id =========================================================
    public String getCourseTrainingTypeId(){

        return new  SQL(){{
            SELECT("*");
            FROM("hrd_course_training_type");
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Count total event category =========================================================
    public String getEventCategoryTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_event_category");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select event category by id =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_event_category");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO : Insert event category =========================================================
    public String insert(){

        return new SQL(){{
            INSERT_INTO("hrd_event_category");
            VALUES("name","#{name}");
            VALUES("course_training_type_id","#{courseTrainingTypeId.id}");
            VALUES("status","#{status}");
        }}.toString();
    }

    //TODO : Delete event category =========================================================
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_event_category");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO : Update event category =========================================================
    public String update(){

        return new SQL(){{
            UPDATE("hrd_event_category");
            SET("name = #{eventCategoryRequest.name}",
                    "course_training_type_id = #{eventCategoryRequest.courseTrainingTypeId.id}");
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Select after delete =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_event_category");
            WHERE("id = #{id}");
        }}.toString();
    }
}
