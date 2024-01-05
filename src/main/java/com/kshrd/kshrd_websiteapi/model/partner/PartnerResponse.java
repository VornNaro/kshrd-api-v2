package com.kshrd.kshrd_websiteapi.model.partner;

import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeName;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeRequest;

public class PartnerResponse {

    private int id;
    private String name;
    private String address;
    private String logo;
    private PartnerTypeDto partnerType;

    public PartnerResponse() {
    }

    public PartnerResponse(int id, String name, String address, String logo, PartnerTypeDto partnerType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.logo = logo;
        this.partnerType = partnerType;
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

    public String getAddress() {
        return address.trim();
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

    @Override
    public String toString() {
        return "PartnerRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", logo='" + logo + '\'' +
                ", partner_type=" + partnerType +
                '}';
    }
}
