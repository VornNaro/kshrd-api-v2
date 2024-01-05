package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.user.UserFilter;
import com.kshrd.kshrd_websiteapi.model.user.UserRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    //TODO: Select all users with pagination =========================================================
    public String selectWithPagination(@Param("username") UserFilter username,@Param("page")  Pagination pagination){

        return new SQL(){{
            SELECT("* FROM hrd_user");
            ORDER_BY("hrd_user.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
            if(username.getUsername()!=null){
                WHERE("hrd_user.username ILIKE '%'||#{username.username}||'%'");
            }
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select user total count =========================================================
    public String selectUserTotalCount(){
        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_user");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select role by id =========================================================
    public String selectRolesById(int id){

        return new SQL(){{
            SELECT("r.id,r.name ");
            FROM ("hrd_role r");
            INNER_JOIN("hrd_user_role ur ON r.id= ur.role.id");
            WHERE("ur.user_id=#{id}");
        }}.toString();
    }

    //TODO: Select user by ID =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_user");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select user by ID after delete =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_user");
            WHERE("id=#{id}");
        }}.toString();
    }

    //TODO: Update user =========================================================
    public String updateUser(int id, UserRequest userRequest){

        return new SQL(){{
            UPDATE("hrd_user");
            SET("username = #{userRequest.username}");
            SET("password = #{userRequest.password}");
            SET("gender = #{userRequest.gender}");
            SET("email = #{userRequest.email}");
            SET("photo = #{userRequest.photo}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select user by username =========================================================
    public String selectUser(){

        return new SQL(){{
            SELECT("*");
            FROM("hrd_user");
            WHERE("username = #{username}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete user =========================================================
    public String deleteUser(int id){

        return new SQL(){{
            UPDATE("hrd_user");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Check type =========================================================
    public String checkIfTypeExisted(){

        return new SQL(){{
            SELECT("id from hrd_role WHERE id=#{id}");
        }}.toString();
    }

    //TODO: Check if username exists =========================================================
    public String checkIfUsernameExisted(){

        return new SQL(){{
            SELECT("username from hrd_user WHERE username = #{username}");
        }}.toString();
    }


}
