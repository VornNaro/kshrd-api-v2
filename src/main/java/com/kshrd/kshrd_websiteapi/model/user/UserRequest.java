package com.kshrd.kshrd_websiteapi.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UserRequest {

    @JsonIgnore
    @ApiModelProperty(value = "user_id", position = 1)
    private String user_id;

    @ApiModelProperty(value = "username", position = 2)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @ApiModelProperty(value = "password", position = 3)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @ApiModelProperty(value = "gender", position = 4)
    @NotBlank(message = "Gender cannot be empty")
    private String gender;

    @ApiModelProperty(value = "email", position = 5)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @ApiModelProperty(value = "photo", position = 6)
    private String photo;
    @JsonIgnore
    private boolean status;
    @ApiModelProperty(value = "role_id", position = 7)
    private int role_id;

    public UserRequest(){

    }

    public UserRequest(String user_id, String username, String password, String gender, String email, String photo, boolean status, int role_id) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.photo = photo;
        this.status = status;
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender.trim();
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email.trim();
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                ", role_id=" + role_id +
                '}';
    }
}
