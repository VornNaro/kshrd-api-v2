package com.kshrd.kshrd_websiteapi.model.user;

import org.springframework.security.core.GrantedAuthority;

public class RoleDto implements GrantedAuthority {

    private int id;
    private String name;

    public RoleDto(){

    }

    public RoleDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}

