package com.kshrd.kshrd_websiteapi.model.announcement;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AnnouncementResponse {

    @ApiModelProperty(value = "id", position = 1)
    private int id;

    @ApiModelProperty(value = "title", position = 2)
    private String title;

    @ApiModelProperty(value = "description", position = 3)
    private String description;

    @ApiModelProperty(value = "thumbnail", position = 4)
    private String thumbnail;

    @ApiModelProperty(value = "content", position = 5)
    private String content;

    @ApiModelProperty(value = "date", position = 6)
    private Date date;

    public AnnouncementResponse() {
    }

    public AnnouncementResponse(int id, String title, String description, String thumbnail, String content, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AnnouncementResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
