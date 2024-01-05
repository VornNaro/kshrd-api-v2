package com.kshrd.kshrd_websiteapi.model.user;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class TokenRequestUser {

    @ApiModelProperty(value = "username", position = 1)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @ApiModelProperty(value = "password", position = 2)
    private String password;

    public TokenRequestUser(){

    }

    public TokenRequestUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
