package com.kshrd.kshrd_websiteapi.model.alumni;

public class AlumniResponse {

    private int id;
    private String name;
    private String major;
    private String workplace;
    private String comment;
    private String photo;
    private String link;

    public AlumniResponse() {
    }

    public AlumniResponse(int id, String name, String major, String workplace, String comment, String photo, String link) {
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
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "AlumniResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", workplace='" + workplace + '\'' +
                ", comment='" + comment + '\'' +
                ", photo='" + photo + '\'' +
                ", link='" + link + '\''+
                '}';
    }
}
