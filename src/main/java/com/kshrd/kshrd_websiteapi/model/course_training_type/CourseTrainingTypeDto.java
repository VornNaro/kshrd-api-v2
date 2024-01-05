package com.kshrd.kshrd_websiteapi.model.course_training_type;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CourseTrainingTypeDto {

    private int id;
    private String name;
    @JsonIgnore
    private boolean status;

    public CourseTrainingTypeDto(){

    }

    public CourseTrainingTypeDto(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseTrainingType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

