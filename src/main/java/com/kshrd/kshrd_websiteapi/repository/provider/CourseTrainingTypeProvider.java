package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeFilter;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CourseTrainingTypeProvider {

    //TODO: Select all course training types with pagination =========================================================
    public String selectWithPagination(@Param("name") CourseTrainingTypeFilter name,@Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("* FROM hrd_course_training_type");
            WHERE("status = true");
            if(name.getName()!=null){
                WHERE("hrd_course_training_type.name ILIKE '%'||#{name.name}||'%'");
            }
            ORDER_BY("hrd_course_training_type.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
        }}.toString();
    }

    //TODO: Get course training type total count =========================================================
    public String getCourseTrainingTypeTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_course_training_type");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Update course training type =========================================================
    public String update(int id,CourseTrainingTypeRequest courseTrainingTypeRequest){

        return new SQL(){{
            UPDATE("hrd_course_training_type");
            SET("name = #{courseTrainingTypeRequest.name}");
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Delete course training type =========================================================
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_course_training_type");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: select course training type by id =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_course_training_type");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: select course training type after delete =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_course_training_type");
            WHERE("id=#{id}");
        }}.toString();
    }
}

