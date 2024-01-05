package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.user.RoleDto;
import com.kshrd.kshrd_websiteapi.model.user.UserDto;
import com.kshrd.kshrd_websiteapi.model.user.UserFilter;
import com.kshrd.kshrd_websiteapi.model.user.UserRequest;
import com.kshrd.kshrd_websiteapi.repository.provider.UserProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    //TODO: Select user by username =====================================================================
    @SelectProvider(value = UserProvider.class,method = "selectUser")
    UserDto loadUserByUsername(String username);

    //TODO: Select roles by id =========================================================
    @Select("SELECT r.id,r.name from hrd_role r\n"+
            "inner join hrd_user_role ur on r.id=ur.role_id\n"+
            "where ur.user_id=#{id}")
    List<RoleDto> selectRolesById(int id);

    //TODO: Select user total count =========================================================
    @SelectProvider(value =UserProvider.class,method = "selectUserTotalCount")
    int selectUserTotalCount();

    //TODO: Select user by id =========================================================
    @SelectProvider(value = UserProvider.class, method = "selectById")
    UserDto selectById(int id);

    //TODO: Select all user with pagination =========================================================
    @SelectProvider(value = UserProvider.class,method = "selectWithPagination")
    List<UserDto> selectWithPagination(@Param("username")UserFilter username,@Param("page") Pagination pagination);


    //TODO: Insert user =====================================================================
    @Insert("INSERT INTO hrd_user (user_id,username,gender,password,email,photo,status)" +
            "VALUES (#{user_id},#{username},#{gender},#{password},#{email},#{photo},true)")
    boolean insertUser(UserRequest userRequest);

    //TODO: Insert user role =====================================================================
    @Insert("INSERT INTO hrd_user_role VALUES (#{userId},#{roleId},true)")
    boolean insertUserRole(int userId,int roleId);

    //TODO: Update user =====================================================================
    @UpdateProvider(value = UserProvider.class,method = "updateUser")
    boolean updateUser(int id,UserRequest userRequest);

    //TODO: Delete user =====================================================================
    @DeleteProvider(value = UserProvider.class,method = "deleteUser")
    boolean deleteUser(int id);

    //TODO: Check if type exists =====================================================================
    @SelectProvider(value = UserProvider.class,method = "checkIfTypeExisted")
    Object checkIfTypeExisted(int id);

    //TODO: Check if username exists =====================================================================
    @SelectProvider(value = UserProvider.class, method = "checkIfUsernameExisted")
    Object checkIfUsernameExisted(String username);

    //TODO: Select user by id after delete =========================================================
    @SelectProvider(value = UserProvider.class, method = "selectAfterDelete")
    UserDto selectAfterDelete(int id);
}

