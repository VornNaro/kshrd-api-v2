package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.user.RoleDto;
import com.kshrd.kshrd_websiteapi.model.user.UserDto;
import com.kshrd.kshrd_websiteapi.model.user.UserFilter;
import com.kshrd.kshrd_websiteapi.model.user.UserRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    //TODO: Insert user =========================================================
    boolean insertUser(UserRequest userRequest);

    //TODO: Check if type exists =========================================================
    boolean checkIfTypeExisted(int id);

    //TODO: Check if username exists =========================================================
    boolean checkIfUsernameExisted(String username);

    //TODO: Select user by id =========================================================
    UserDto selectById(int id);

    //TODO: Select user by id after delete =========================================================
    UserDto selectAfterDelete(int id);

    //TODO: Insert user_role =========================================================
    boolean insertUserRole(int userId,int roleId);

    //TODO: Select all users =========================================================
    List<UserDto> selectWithPagination(UserFilter username,Pagination pagination);

    //TODO: Update user =========================================================
    boolean updateUser(int id,UserRequest userRequest);

    //TODO: Delete user =========================================================
    boolean deleteUser(int id);

    //TODO: Select roles by id =========================================================
    List<RoleDto> selectRolesById(int id);

}
