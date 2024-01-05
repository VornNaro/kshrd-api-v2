package com.kshrd.kshrd_websiteapi.model.announcement;

public class AnnouncementFilter {

    private String title;

    public AnnouncementFilter() {
    }

    public AnnouncementFilter(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AnnouncementFilter{" +
                "content='" + title + '\'' +
                '}';
    }
}
