package com.kshrd.kshrd_websiteapi.model.event;

import io.swagger.annotations.ApiModelProperty;

public class EventDescription {

    @ApiModelProperty(value = "image", position = 1)
    private String image;

    @ApiModelProperty(value = "description", position = 2)
    private String description;

    public EventDescription() {
    }

    public EventDescription(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
