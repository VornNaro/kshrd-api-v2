package com.kshrd.kshrd_websiteapi.model.careerpath;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CareerPathRequest {

    @JsonIgnore
    @ApiModelProperty(value = "id", position = 1)
    private int id;

    @ApiModelProperty(value = "parentID", position = 2)
    private int parent_id;

    @ApiModelProperty(value = "title", position = 3)
    @NotBlank(message = "Title cannot be empty")
    private String  title;

    @ApiModelProperty(value = "description", position = 4)
    @NotBlank(message = "Description cannot be empty")
    private String description;

    @ApiModelProperty(value = "detail", position = 5)
    private String detail;

    @ApiModelProperty(value = "photo", position = 6)
    private String photo;

    public CareerPathRequest() {
    }

    public CareerPathRequest(int id, int parent_id, String title, String description, String detail, String photo) {
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
        return "CareerPathRequest{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
