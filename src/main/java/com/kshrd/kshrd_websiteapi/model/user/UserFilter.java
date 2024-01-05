package com.kshrd.kshrd_websiteapi.model.user;

public class UserFilter {
    private String username;

    public UserFilter() {
    }

    public UserFilter(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "name='" + username + '\'' +
                '}';
    }
}
