package com.kshrd.kshrd_websiteapi.model.partner_type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;

public class PartnerTypeRequest {

    @JsonIgnore
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    public PartnerTypeRequest() {
    }

    public PartnerTypeRequest(int id, @NotBlank(message = "Name cannot be empty") String name) {
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
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PartnerTypeRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
