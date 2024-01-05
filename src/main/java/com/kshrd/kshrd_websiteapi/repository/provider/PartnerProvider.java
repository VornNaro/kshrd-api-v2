package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.partner.PartnerFilter;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class PartnerProvider {

    //TODO: Select all partners with pagination =========================================================
    public String selectWithPagination(@Param ("partnerTypeId") Integer partnerTypeId,@Param("name") PartnerFilter name, @Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("p.id,p.name,p.address,p.logo,p.partner_type,pt.id,pt.name");
            FROM("hrd_partner AS p");
            INNER_JOIN("hrd_partner_type AS pt ON p.partner_type = pt.id");
            WHERE("p.status = true AND pt.status = true");

            if(name.getName()!=null){

                WHERE("p.name ILIKE '%'||#{name.name}||'%'");

            }

            if(partnerTypeId!=null){

                WHERE("p.partner_type = #{partnerTypeId}");

            }

            ORDER_BY("p.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
        }}.toString();
    }


    //TODO: Select partner by id =========================================================
    public String selectById(int id){
        return new SQL(){{
            SELECT("* FROM hrd_partner");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select Partner Total Count =========================================================
    public String getPartnerTotalCount(){
        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_partner");
            WHERE("status = true");
            AND();
            WHERE("partner_type = #{partner_type}");
        }}.toString();
    }

    //TODO: Select partner type by id =========================================================
    public String getPartnerTypeById(int id){

        return new  SQL(){{
            SELECT("*");
            FROM("hrd_partner_type");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete partner =========================================================
    public String delete(int id){

        return new SQL(){{
            UPDATE("hrd_partner");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Update partner =========================================================
    public String update(int id, PartnerRequest partnerRequest){

        return new SQL(){{

            UPDATE("hrd_partner");
            SET("name = #{partnerRequest.name}, address = #{partnerRequest.address}, logo = #{partnerRequest.logo}," +
                    " partner_type = #{partnerRequest.partnerType.id}");
            WHERE("id = #{id}");
            AND();
            WHERE("status =true");
        }}.toString();
    }

    //TODO: Select partner type name by id =========================================================
    public String getPartnerTypeName(){

        return new  SQL(){{
            SELECT("name");
            FROM("hrd_partner_type");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select after delete
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_partner");
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Check type
    public String checkIfTypeExisted(){

        return new SQL(){{
            SELECT("id from hrd_partner_type WHERE id=#{id}");
        }}.toString();
    }

    //TODO: Select all partners with pagination =========================================================
    public String selectAll(@Param("name") PartnerFilter name, @Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("p.id,p.name,p.address,p.logo,p.partner_type,pt.id,pt.name");
            FROM("hrd_partner AS p");
            INNER_JOIN("hrd_partner_type AS pt ON p.partner_type = pt.id");
            WHERE("p.status = true AND pt.status = true");

            if(name.getName()!=null){
                WHERE("p.name ILIKE '%'||#{name.name}||'%'");
            }

            ORDER_BY("p.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
        }}.toString();
    }

    //TODO: Select all Partner Total Count =========================================================
    public String getAllPartnerTotalCount(){
        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_partner");
            WHERE("status = true");
        }}.toString();
    }

}
