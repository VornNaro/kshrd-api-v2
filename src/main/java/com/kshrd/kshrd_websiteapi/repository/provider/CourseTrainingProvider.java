package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingFilter;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingRequest;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerFilter;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CourseTrainingProvider {

    //TODO: Select all course trainings with pagination =========================================================
    public String selectWithPagination(@Param("courseTypeId") Integer courseTypeId,@Param("name") CourseTrainingFilter name, @Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("c.id,c.name,c.description,c.logo,c.file,c.course_training_type_id,ct.id,ct.name");
            FROM("hrd_course_training AS c");
            INNER_JOIN("hrd_course_training_type AS ct ON c.course_training_type_id = ct.id");
            WHERE("c.status = true AND ct.status = true");

            if(name.getName()!=null) {
                WHERE("c.name ILIKE '%'||#{name.name}||'%'");
            }
            if(courseTypeId!=null){
                WHERE("c.course_training_type_id = #{courseTypeId}");
            }
            ORDER_BY("c.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
        }}.toString();
    }

    //TODO: Select course training by ID =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_course_training");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Get course training total count =========================================================
    public String getCourseTrainingTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_course_training");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Update course training
    public String update(int id, CourseTrainingRequest courseTrainingRequest){

        return new SQL(){{
            UPDATE("hrd_course_training");
            SET("name = #{courseTrainingRequest.name}, description = #{courseTrainingRequest.description}, logo = #{courseTrainingRequest.logo}, file = #{courseTrainingRequest.file}," +
                    "course_training_type_id = #{courseTrainingRequest.courseTrainingTypeId.id}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete course training
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_course_training");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Get course training type by id =========================================================
    public String getCourseTrainingTypeId(){

        return new  SQL(){{
            SELECT("*");
            FROM("hrd_course_training_type");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Get course training type name by id =========================================================
    public String getCourseTrainingTypeName(){

        return new  SQL(){{
            SELECT("name");
            FROM("hrd_course_training_type");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Get course training type name by id =========================================================
    public String getCourseTrainingTypeIdName(){

        return new  SQL(){{
            SELECT("id");
            FROM("hrd_course_training_type");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select course training after delete =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_course_training");
            WHERE("id=#{id}");
        }}.toString();
    }

    //TODO: Check Type
    public String checkIfTypeExisted(){

        return new SQL(){{
            SELECT("id from hrd_course_training_type WHERE id=#{id}");
        }}.toString();
    }

    //TODO: Select all course trainings without pagination =========================================================
    public String selectAll(){
        return new SQL(){{
            SELECT("c.id,c.name,c.description,c.logo,c.file,c.course_training_type_id,ct.id,ct.name");
            FROM("hrd_course_training AS c");
            INNER_JOIN("hrd_course_training_type AS ct ON c.course_training_type_id = ct.id");
            WHERE("c.status = true AND ct.status = true");
            ORDER_BY("c.id DESC");
        }}.toString();
    }
}
