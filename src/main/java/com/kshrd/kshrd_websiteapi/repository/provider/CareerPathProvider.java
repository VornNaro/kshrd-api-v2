package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathFilter;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CareerPathProvider {

    //TODO: Select all career path with pagination =========================================================
    public String selectWithPagination(@Param("title") CareerPathFilter title, @Param("page") Pagination pagination) {

        return new SQL(){{
            SELECT("*");
            FROM("hrd_career_path");
            WHERE("status = true");
            if(title.getTitle()!=null){
                WHERE("hrd_career_path.title ILIKE '%'||#{title.title}||'%'");
            }
            ORDER_BY("hrd_career_path.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
        }}.toString();
    }

    //TODO: Get career path total =========================================================
    public String getCareerPathTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id)");
            FROM("hrd_career_path");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select career path by ID =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_career_path");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete career path =========================================================
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_career_path");
            SET("status =  false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Find career path by id =========================================================
    public String findById(int id){

        return new SQL(){{
            SELECT("*");
            FROM("hrd_career_path");
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Update career path =========================================================
    public String update(int id, CareerPathRequest careerPathRequest){

        return new SQL(){{
            UPDATE("hrd_career_path");
            SET("parent_id = #{careerPathRequest.parent_id}, title = #{careerPathRequest.title}, description = #{careerPathRequest.description}," +
                    "detail = #{careerPathRequest.detail}, photo = #{careerPathRequest.photo}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select career path after delete =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_career_path");
            WHERE("id=#{id}");
        }}.toString();
    }

    //TODO: check If Parent ID Not Existed =========================================================
    public String checkIfParentIDNotExisted(){
        return new SQL(){{
            SELECT("id from hrd_career_path WHERE id=#{id}");
        }}.toString();
    }

    //TODO: Select all career path with pagination =========================================================
    public String selectAll(@Param("title") CareerPathFilter title) {

        return new SQL(){{
            SELECT("*");
            FROM("hrd_career_path");
            WHERE("status = true");
            if(title.getTitle()!=null){
                WHERE("hrd_career_path.title ILIKE '%'||#{title.title}||'%'");
            }
            ORDER_BY("hrd_career_path.id DESC");
        }}.toString();
    }
}
