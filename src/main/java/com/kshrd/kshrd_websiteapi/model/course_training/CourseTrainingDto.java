package com.kshrd.kshrd_websiteapi.model.course_training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;

public class CourseTrainingDto {

    private int id;
    private String name;
    private String description;
    private String logo;
    private String file;
    private CourseTrainingTypeDto courseTrainingTypeId;
    @JsonIgnore
    private boolean status;

    public CourseTrainingDto(){

    }
    public CourseTrainingDto(int id, String name, String description, String logo, String file, CourseTrainingTypeDto courseTrainingTypeId,boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.file = file;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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
}
