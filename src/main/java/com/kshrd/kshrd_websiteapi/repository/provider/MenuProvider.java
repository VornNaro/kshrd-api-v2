package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.menu.MenuDto;
import com.kshrd.kshrd_websiteapi.model.menu.MenuFilter;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class MenuProvider {

    //TODO: Select all menus =========================================================
    public String selectAll(@Param("name") MenuFilter name){

        return new SQL(){{
            SELECT("* FROM hrd_menu");
            WHERE("status = true");
            AND();
            WHERE("parent_id=0");
            if(name.getName()!=null){
                WHERE("hrd_menu.name ILIKE '%'||#{name.name}||'%'");
            }
            ORDER_BY("hrd_menu.menu_order");
        }}.toString();
    }

    //TODO: Select menu by id =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_menu");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select menu by id =========================================================
    public String selectByParentId(int parentId){

        return new SQL(){{
            SELECT("* FROM hrd_menu");
            WHERE("parent_id=#{parentId}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Get menu total count =========================================================
    public String getMenuTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_menu");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete menu =========================================================
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_menu");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }
    //TODO: Update menu =========================================================
    public String update(int id, MenuDto menuDto){

        return new SQL(){{
            UPDATE("hrd_menu");
            SET("parent_id = #{menuDto.parent_id}, menu_order = #{menuDto.menu_order}, " +
                    "name = #{menuDto.name}, link = #{menuDto.link}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select menu by id =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_menu");
            WHERE("id=#{id}");
        }}.toString();
    }

    //TODO: Select menu by order id =========================================================
    public String selectByOrder(int menu_order){

        return new SQL(){{
            SELECT("* FROM hrd_menu");
            WHERE("menu_order=#{menu_order}");
        }}.toString();
    }

    //TODO: Select max order id =========================================================
    public String selectMaxOrder(){

        return new SQL(){{
            SELECT("MAX(menu_order) FROM hrd_menu");
        }}.toString();
    }

    //TODO: Change order when duplicated menu order =========================================================
    public String changeOrderWhenDuplicated(@Param("menu_order")int menu_order,@Param("maxOrder")int maxOrder){

        return new SQL(){{
            UPDATE("hrd_menu SET menu_order=#{maxOrder}");
            WHERE("menu_order=#{menu_order}");
        }}.toString();
    }

    //TODO: Select Sub Menu
    public String selectSubMenu(){
        return new SQL(){{
            SELECT("*");
            FROM("hrd_menu");
            WHERE("id = {id}");
        }}.toString();
    }

    //TODO: Select all menus with pagination =========================================================
    public String selectAllMenuWithPagination(@Param("name") MenuFilter name,@Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("* FROM hrd_menu");
            WHERE("status = true");
            if(name.getName()!=null){
                WHERE("hrd_menu.name ILIKE '%'||#{name.name}||'%'");
            }
            ORDER_BY("hrd_menu.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
        }}.toString();
    }

    //
    public String selectParentIDByID(int id){
        return new SQL(){{
            SELECT("parent_id FROM hrd_menu");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");

        }}.toString();
    }

    public String selectParentByParentID(int id){
        return new SQL(){{
            SELECT("id,name FROM hrd_menu");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");

        }}.toString();
    }
}
