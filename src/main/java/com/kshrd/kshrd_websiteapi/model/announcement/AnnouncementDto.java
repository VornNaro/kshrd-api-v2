package com.kshrd.kshrd_websiteapi.model.announcement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class AnnouncementDto {

    private int id;
    private String title;
    private String description;
    private String thumbnail;
    private String content;
    private Date date;
    @JsonIgnore
    private boolean status;

    public AnnouncementDto() {
    }

    public AnnouncementDto(int id, String title, String description, String thumbnail, String content, Date date, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.content = content;
        this.date = date;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AnnouncementDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
