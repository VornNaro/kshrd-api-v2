package com.kshrd.kshrd_websiteapi.model.careerpath;

import io.swagger.annotations.ApiModelProperty;

public class CareerPathResponse {

    @ApiModelProperty(value = "id", position = 1)
    private int id;

    @ApiModelProperty(value = "parentID", position = 2)
    private int parent_id;

    @ApiModelProperty(value = "title", position = 2)
    private String  title;

    @ApiModelProperty(value = "description", position = 2)
    private String description;

    @ApiModelProperty(value = "detail", position = 2)
    private String detail;
    private String photo;

    public CareerPathResponse() {
    }

    public CareerPathResponse(int id, int parent_id, String title, String description, String detail, String photo) {
        this.id = id;
        this.parent_id = parent_id;
        this.title = title;
        this.description = description;
        this.detail = detail;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getTitle() {
        return title.trim();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description.trim();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail.trim();
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public String toString() {
        return "careerpathResponse{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
