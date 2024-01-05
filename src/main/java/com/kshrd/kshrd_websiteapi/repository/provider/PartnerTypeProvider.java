package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeFilter;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.security.core.parameters.P;

public class PartnerTypeProvider {

    //TODO: Select all partner types with pagination =========================================================
    public String selectWithPagination(@Param("name") PartnerTypeFilter name,@Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("* FROM hrd_partner_type");
            WHERE("status = true");
            if(name.getName()!=null){
                WHERE("hrd_partner_type.name ILIKE '%'||#{name.name}||'%'");
            }
            ORDER_BY("hrd_partner_type.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
        }}.toString();
    }

    //TODO: Select partner type by ID =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_partner_type");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select partner type total count =========================================================
    public String getPartnerTypeTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_partner_type");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete partner type =========================================================
    public String delete(int id){

        return new SQL(){{
            UPDATE("hrd_partner_type");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Update partner type =========================================================
    public String update(int id, PartnerTypeRequest partnerTypeRequest){

        return new SQL(){{
            UPDATE("hrd_partner_type");
            SET("name = #{partnerTypeRequest.name}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select partner type by id =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_partner_type");
            WHERE("id=#{id}");
        }}.toString();
    }
}
