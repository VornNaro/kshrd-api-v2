package com.kshrd.kshrd_websiteapi.model.alumni;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AlumniRequest {

    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "name", position = 1)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ApiModelProperty(value = "major", position = 2)
    @NotBlank(message = "Major cannot be empty")
    private String major;

    @ApiModelProperty(value = "workplace", position = 3)
    @NotBlank(message = "Workplace cannot be empty")
    private String workplace;

    @ApiModelProperty(value = "comment", position = 4)
    @NotBlank(message = "Comment cannot be empty")
    private String comment;
    
    @ApiModelProperty(value = "photo", position = 5)
    private String photo;

    @NotBlank(message = "Link cannot be empty")
    @ApiModelProperty(value = "link", position = 6)
    private String link;

    public AlumniRequest() {
    }

    public AlumniRequest(int id, @NotBlank(message = "Name cannot be empty") String name, @NotBlank(message = "Major cannot be empty") String major, String workplace, @NotBlank(message = "Comment cannot be empty") String comment, String photo, @NotBlank(message = "Link cannot be empty") String link) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.workplace = workplace;
        this.comment = comment;
        this.photo = photo;
        this.link = link;
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

    public String getMajor() {
        return major.trim();
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorkplace() {
        return workplace.trim();
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getComment() {
        return comment.trim();
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
        return link.trim();
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "AlumniRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", workplace='" + workplace + '\'' +
                ", comment='" + comment + '\'' +
                ", photo='" + photo + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
