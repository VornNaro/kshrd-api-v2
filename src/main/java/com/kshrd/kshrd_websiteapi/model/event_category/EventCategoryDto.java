package com.kshrd.kshrd_websiteapi.model.event_category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;

public class EventCategoryDto {

    private int id;
    private String name;
    private CourseTrainingTypeDto courseTrainingTypeId;
    @JsonIgnore
    private boolean status;

    public EventCategoryDto() {
    }

    public EventCategoryDto(int id, String name, CourseTrainingTypeDto courseTrainingTypeId, boolean status) {
        this.id = id;
        this.name = name;
        this.courseTrainingTypeId = courseTrainingTypeId;
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

    public CourseTrainingTypeDto getCourseTrainingTypeId() {
        return courseTrainingTypeId;
    }

    public void setCourseTrainingTypeId(CourseTrainingTypeDto courseTrainingTypeId) {
        this.courseTrainingTypeId = courseTrainingTypeId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventCategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseTrainingTypeId=" + courseTrainingTypeId +
                ", status=" + status +
                '}';
    }
}
