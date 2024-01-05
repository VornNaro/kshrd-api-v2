package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.alumni.AlumniDto;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniFilter;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class AlumniProvider {

    //TODO: Select all alumni with pagination =========================================================
    public String selectWithPagination(@Param("name") AlumniFilter name, @Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("* FROM hrd_alumni");
            WHERE("status = true");
            ORDER_BY("hrd_alumni.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
            if(name.getName()!=null){
                WHERE("hrd_alumni.name ILIKE '%'||#{name.name}||'%'");
            }
        }}.toString();
    }

    //TODO: Select alumni by id =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_alumni");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Count total alumni =========================================================
    public String getAlumniTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_alumni");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete alumni =========================================================
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_alumni");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Update alumni =========================================================
    public String update(int id, AlumniRequest alumniRequest){

        return new SQL(){{
            UPDATE("hrd_alumni");
            SET("name = #{alumniRequest.name}, major = #{alumniRequest.major}, workplace = #{alumniRequest.workplace}, photo = #{alumniRequest.photo}, comment = #{alumniRequest.comment}, link = #{alumniRequest.link}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select alumni after delete by id =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_alumni");
            WHERE("id=#{id}");
        }}.toString();
    }
}
