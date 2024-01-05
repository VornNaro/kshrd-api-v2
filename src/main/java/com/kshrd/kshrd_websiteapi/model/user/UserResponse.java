package com.kshrd.kshrd_websiteapi.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UserResponse {

    @ApiModelProperty(value = "id", position = 1)
    private int id;
    @ApiModelProperty(value = "user_id", position = 2)
    private String user_id;

    @ApiModelProperty(value = "username", position = 3)
    private String username;

    @ApiModelProperty(value = "gender", position = 4)
    private String gender;

    @JsonIgnore
    @ApiModelProperty(value = "password", position = 5)
    private String password;

    @ApiModelProperty(value = "email", position = 6)
    private String email;

    @ApiModelProperty(value = "photo", position = 7)
    private String photo;

    private List<RoleDto> roles;

    public UserResponse(){

    }

    public UserResponse(int id, String user_id, String username, String gender, String password, String email, String photo, List<RoleDto> roles) {

        this.id = id;
        this.user_id = user_id;
        this.username = username;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username.trim();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender.trim();
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email.trim();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", roles=" + roles +
                '}';
    }
}
