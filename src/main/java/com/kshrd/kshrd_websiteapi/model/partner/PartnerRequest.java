package com.kshrd.kshrd_websiteapi.model.partner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeId;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeRequest;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class PartnerRequest {

    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "name", position = 1)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ApiModelProperty(value = "address", position = 2)
    @NotBlank(message = "Address cannot be empty")
    private String address;

    @ApiModelProperty(value = "logo", position = 3)
//    @NotBlank(message = "Logo cannot be empty")
    private String logo;

    @ApiModelProperty(value = "partner type id", position = 4)
    private PartnerTypeId partnerType;

    public PartnerRequest() {
    }

    public PartnerRequest(int id, @NotBlank(message = "Name cannot be empty") String name, String address, @NotBlank(message = "Logo order cannot be empty") String logo, PartnerTypeId partnerType) {
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

    public PartnerTypeId getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerTypeId partnerType) {
        this.partnerType = partnerType;
    }

    @Override
    public String toString() {
        return "PartnerRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", logo='" + logo + '\'' +
                ", partnerType=" + partnerType +
                '}';
    }
}
