package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.user.RoleDto;
import com.kshrd.kshrd_websiteapi.model.user.UserDto;
import com.kshrd.kshrd_websiteapi.model.user.UserFilter;
import com.kshrd.kshrd_websiteapi.model.user.UserRequest;
import com.kshrd.kshrd_websiteapi.repository.UserRepository;
import com.kshrd.kshrd_websiteapi.service.UserService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO: Load user by username =========================================================
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userModel=userRepository.loadUserByUsername(username);
        userModel.setRoles(userRepository.selectRolesById(userModel.getId()));
        return userModel;
    }

    //TODO: Insert user =========================================================
    @Override
    public boolean insertUser(UserRequest userRequest) {
        return userRepository.insertUser(userRequest);
    }

    //TODO: Check if type exists =========================================================
    @Override
    public boolean checkIfTypeExisted(int id) {

        boolean existed=false;
        if(userRepository.checkIfTypeExisted(id)!=null)
            existed=true;
        return existed;
    }

    //TODO: Check if username exists =========================================================
    @Override
    public boolean checkIfUsernameExisted(String username) {

        boolean existed=false;
        if(userRepository.checkIfUsernameExisted(username)!=null)
            existed=true;
        return existed;
    }

    //TODO: Select user by id =========================================================
    @Override
    public UserDto selectById(int id) {

        UserDto userDto = userRepository.selectById(id);
        if(userDto==null){
            return null;
        }
        else {
            userDto.setRoles(userRepository.selectRolesById(userDto.getId()));
            return userDto;
        }
    }

    @Override
    public UserDto selectAfterDelete(int id) {
        UserDto userDto = userRepository.selectAfterDelete(id);
        if(userDto==null){
            return null;
        }
        else {
            userDto.setRoles(userRepository.selectRolesById(userDto.getId()));
            return userDto;
        }
    }

    //TODO: Insert user_role =========================================================
    @Override
    public boolean insertUserRole(int userId, int roleId) {
        return userRepository.insertUserRole(userId,roleId);
    }

    //TODO: Select all users =========================================================
    @Override
    public List<UserDto> selectWithPagination(UserFilter username, Pagination pagination) {

        pagination.setTotalCount(userRepository.selectUserTotalCount());
        List<UserDto> noRoles = userRepository.selectWithPagination(username,pagination);
        List<UserDto> addRoles = new ArrayList<>();

        for ( UserDto userDto : noRoles){
            userDto.setRoles(userRepository.selectRolesById(userDto.getId()));
            userDto.setPassword(userDto.getPassword());
            addRoles.add(userDto);
        }
        return addRoles;
    }

    //TODO: Update user =========================================================
    @Override
    public boolean updateUser(int id,UserRequest userRequest) {
        return userRepository.updateUser(id,userRequest);
    }

    //TODO: Delete user =========================================================
    @Override
    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    //TODO: Select roles by id =========================================================
    @Override
    public List<RoleDto> selectRolesById(int id) {
        return userRepository.selectRolesById(id);
    }

}