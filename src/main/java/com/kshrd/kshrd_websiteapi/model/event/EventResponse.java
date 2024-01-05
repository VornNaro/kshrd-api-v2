package com.kshrd.kshrd_websiteapi.model.event;


import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryDto;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationDto;

import java.util.List;

public class EventResponse {

    private int id;
    private String name;
    private List<EventDescription> eventDescription;
    private String video;
    private EventCategoryDto category;
    private GenerationDto generation;

    public EventResponse() {
    }

    public EventResponse(int id, String name, List<EventDescription> eventDescription, String video, EventCategoryDto category, GenerationDto generation) {
        this.id = id;
        this.name = name;
        this.eventDescription = eventDescription;
        this.video = video;
        this.category = category;
        this.generation = generation;
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

    public List<EventDescription> getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(List<EventDescription> eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public EventCategoryDto getCategory() {
        return category;
    }

    public void setCategory(EventCategoryDto category) {
        this.category = category;
    }

    public GenerationDto getGeneration() {
        return generation;
    }

    public void setGeneration(GenerationDto generation) {
        this.generation = generation;
    }


    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventDescription=" + eventDescription +
                ", video='" + video + '\'' +
                ", categoryId=" + category +
                ", generationId=" + generation +
                '}';
    }
}
