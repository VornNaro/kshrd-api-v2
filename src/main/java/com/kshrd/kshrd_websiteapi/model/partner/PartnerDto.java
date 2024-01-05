package com.kshrd.kshrd_websiteapi.model.partner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;

public class PartnerDto {

    private int id;
    private String name;
    private String address;
    private String logo;
    private PartnerTypeDto partnerType;
    @JsonIgnore
    private boolean status;

    public PartnerDto() {
    }

    public PartnerDto(int id, String name, String address, String logo, PartnerTypeDto partnerType, boolean status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.logo = logo;
        this.partnerType = partnerType;
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public PartnerTypeDto getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerTypeDto partnerType) {
        this.partnerType = partnerType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PartnerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", logo='" + logo + '\'' +
                ", partner_type=" + partnerType +
                ", status=" + status +
                '}';
    }
}
