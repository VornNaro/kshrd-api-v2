package com.kshrd.kshrd_websiteapi.model.partner_type;

public class PartnerTypeResponse {

    private int id;
    private String name;

    public PartnerTypeResponse() {
    }

    public PartnerTypeResponse(int id, String name) {
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
        return "PartnerTypeResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

