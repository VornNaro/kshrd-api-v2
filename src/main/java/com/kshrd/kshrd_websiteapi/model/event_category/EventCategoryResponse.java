package com.kshrd.kshrd_websiteapi.model.event_category;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;

public class EventCategoryResponse {

    private int id;
    private String name;
    private CourseTrainingTypeDto courseTrainingTypeId;

    public EventCategoryResponse() {
    }

    public EventCategoryResponse(int id, String name, CourseTrainingTypeDto courseTrainingTypeId) {
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

    public CourseTrainingTypeDto getCourseTrainingTypeId() {
        return courseTrainingTypeId;
    }

    public void setCourseTrainingTypeId(CourseTrainingTypeDto courseTrainingTypeId) {
        this.courseTrainingTypeId = courseTrainingTypeId;
    }

    @Override
    public String toString() {
        return "EventCategoryResponse{" +
                "id=" + id +
                ", category='" + name + '\'' +
                ", courseTrainingType='" + courseTrainingTypeId + '\'' +
                '}';
    }
}
