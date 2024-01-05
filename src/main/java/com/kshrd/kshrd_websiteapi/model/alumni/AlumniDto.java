package com.kshrd.kshrd_websiteapi.model.alumni;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AlumniDto {

    private int id;
    private String name;
    private String major;
    private String workplace;
    private String comment;
    private String photo;
    private String link;
    @JsonIgnore
    private boolean status;

    public AlumniDto() {
    }

    public AlumniDto(int id, String name, String major, String workplace, String comment, String photo, String link, boolean status) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.workplace = workplace;
        this.comment = comment;
        this.photo = photo;
        this.link = link;
        this.status = status;
    }

    public AlumniDto(int id) {
        this.id = id;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AlumniDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", workplace='" + workplace + '\'' +
                ", comment='" + comment + '\'' +
                ", photo='" + photo + '\'' +
                ", link='" + link + '\'' +
                ", status=" + status +
                '}';
    }
}
