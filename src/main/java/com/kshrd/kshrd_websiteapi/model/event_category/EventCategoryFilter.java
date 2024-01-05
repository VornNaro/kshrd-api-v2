package com.kshrd.kshrd_websiteapi.model.event_category;

import io.swagger.models.auth.In;

public class EventCategoryFilter {

    private String name;

    public EventCategoryFilter() {
    }

    public EventCategoryFilter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
