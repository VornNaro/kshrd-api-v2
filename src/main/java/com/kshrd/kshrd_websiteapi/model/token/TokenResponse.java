package com.kshrd.kshrd_websiteapi.model.token;

import com.kshrd.kshrd_websiteapi.rest.message.JwtResponse;

public class TokenResponse {

    private String username;
    private String password;
    private JwtResponse token;
    private String photo;

    public TokenResponse(){

    }

    public TokenResponse(String username, String password, JwtResponse token, String photo) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.photo = photo;
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

    public JwtResponse getToken() {
        return token;
    }

    public void setToken(JwtResponse token) {
        this.token = token;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token=" + token +
                ", photo='" + photo + '\'' +
                '}';
    }
}
