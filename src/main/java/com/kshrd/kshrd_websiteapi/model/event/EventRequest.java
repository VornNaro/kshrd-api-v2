package com.kshrd.kshrd_websiteapi.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryId;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationId;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class EventRequest {
    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "name", position = 1)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ApiModelProperty(value = "eventDescription", position = 2)
    private List<EventDescription> eventDescription;

    @ApiModelProperty(value = "video", position = 3)
    private String video;

    @ApiModelProperty(value = "category", position = 4)
    private EventCategoryId category;

    @ApiModelProperty(value = "generation", position = 5)
    private GenerationId generation;


    public EventRequest() {
    }

    public EventRequest(int id, @NotBlank(message = "Name cannot be empty") String name, List<EventDescription> eventDescription, String video, EventCategoryId category, GenerationId generation) {
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
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventDescription> getEventDescription() {
        return eventDescription;
    }

    @JsonProperty("eventDescription")
    public void setEventDescription(List<EventDescription> eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getVideo() {
        return video.trim();
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public EventCategoryId getCategory() {
        return category;
    }

    public void setCategory(EventCategoryId category) {
        this.category = category;
    }

    public GenerationId getGeneration() {
        return generation;
    }

    public void setGeneration(GenerationId generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "EventRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventDescription=" + eventDescription +
                ", video='" + video + '\'' +
                ", category=" + category +
                ", generation=" + generation +
                '}';
    }
}
