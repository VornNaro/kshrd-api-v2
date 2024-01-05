package com.kshrd.kshrd_websiteapi.model.careerpath;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CareerPathDto {

    private int id;
    private int parent_id;
    private String  title;
    private String description;
    private String detail;
    private String photo;
    @JsonIgnore
    private boolean status;

    public CareerPathDto() {
    }

    public CareerPathDto(int id, int parent_id, String title, String description, String detail, String photo, boolean status) {
        this.id = id;
        this.parent_id = parent_id;
        this.title = title;
        this.description = description;
        this.detail = detail;
        this.photo = photo;
        this.status = status;
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "careerpathDto{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                '}';
    }
}
