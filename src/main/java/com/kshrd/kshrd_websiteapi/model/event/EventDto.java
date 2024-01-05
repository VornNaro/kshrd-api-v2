package com.kshrd.kshrd_websiteapi.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryDto;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationDto;

import java.util.List;

public class EventDto {

    private int id;
    private String name;
    private List<EventDescription> eventDescription;
    private String video;
    private EventCategoryDto category;
    private GenerationDto generation;
    @JsonIgnore
    private boolean status;

    public EventDto() {
    }

    public EventDto(int id, String name, List<EventDescription> eventDescription, String video, EventCategoryDto category, GenerationDto generation, boolean status) {
        this.id = id;
        this.name = name;
        this.eventDescription = eventDescription;
        this.video = video;
        this.category = category;
        this.generation = generation;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventDescription=" + eventDescription +
                ", video='" + video + '\'' +
                ", category=" + category +
                ", generation=" + generation +
                ", status=" + status +
                '}';
    }
}
