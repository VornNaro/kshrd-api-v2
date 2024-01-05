package com.kshrd.kshrd_websiteapi.model.event_category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeId;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class EventCategoryRequest {

    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "name", position = 1)
    @NotBlank(message = "Name cannot be empty")
    private String name;
    
    private CourseTrainingTypeId courseTrainingTypeId;

    public EventCategoryRequest() {
    }

    public EventCategoryRequest(int id, @NotBlank(message = "File cannot be empty") String name, CourseTrainingTypeId courseTrainingTypeId) {
        this.id = id;
        this.name = name;
        this.courseTrainingTypeId = courseTrainingTypeId;
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

    public CourseTrainingTypeId getCourseTrainingTypeId() {
        return courseTrainingTypeId;
    }

    public void setCourseTrainingTypeId(CourseTrainingTypeId courseTrainingTypeId) {
        this.courseTrainingTypeId = courseTrainingTypeId;
    }

    @Override
    public String toString() {
        return "EventCategoryRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseTrainingTypeId=" + courseTrainingTypeId +
                '}';
    }
}
