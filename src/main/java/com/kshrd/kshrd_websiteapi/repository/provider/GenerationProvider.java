package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.generation.GenerationFilter;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class GenerationProvider {

    //TODO : Select all generations with pagination =========================================================
    public String selectWithPagination(@Param("name")GenerationFilter name, @Param("page")Pagination pagination){

        return new SQL(){{
            SELECT("*");
            FROM("hrd_generation");
            WHERE("status = true");
            ORDER_BY("hrd_generation.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
            if(name.getName()!=null){
                WHERE("hrd_generation.name ILIKE '%'||#{name.name}||'%'");
            }
        }}.toString();
    }

    //TODO: Count total generation =========================================================
    public String getGenerationTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_generation");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select generation by id =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_generation");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO : Insert event category =========================================================
//    public String insert(){
//        return new SQL(){{
//            INSERT_INTO("hrd_generation");
//            VALUES("name","#{name}");
//            VALUES("start_year","#{startYear}");
//            VALUES("end_year","#{endYear}");
//            VALUES("status","#{status}");
//        }}.toString();
//    }

    //TODO : Delete event category =========================================================
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_generation");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO : Update event category =========================================================
    public String update(){

        return new SQL(){{
            UPDATE("hrd_generation");
            SET("name = #{generationRequest.name}",
                    "start_year = #{generationRequest.startYear}",
                    "end_year = #{generationRequest.endYear}"
            );
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Select generation by id =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_generation");
            WHERE("id=#{id}");
        }}.toString();
    }

    //TODO : Select all generations =========================================================
    public String selectAll(@Param("name")GenerationFilter name){

        return new SQL(){{
            SELECT("*");
            FROM("hrd_generation");
            WHERE("status = true");
            ORDER_BY("hrd_generation.id DESC");
            if(name.getName()!=null){
                WHERE("hrd_generation.name ILIKE '%'||#{name.name}||'%'");
            }
        }}.toString();
    }
}
