package com.kshrd.kshrd_websiteapi.model.course_training;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import io.swagger.annotations.ApiModelProperty;

public class CourseTrainingResponse {

    @ApiModelProperty(value = "id", position = 1)
    private int id;

    @ApiModelProperty(value = "name", position = 2)
    private String name;

    @ApiModelProperty(value = "description", position = 3)
    private String description;

    @ApiModelProperty(value = "logo", position = 4)
    private String logo;

    @ApiModelProperty(value = "file", position = 5)
    private String file;

    @ApiModelProperty(value = "courseTrainingTypeId", position = 6)
    private CourseTrainingTypeDto courseTrainingTypeId;

    public CourseTrainingResponse(){

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

    @Override
    public String toString() {
        return "CourseTrainingResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", file='" + file + '\'' +
                ", courseTrainingTypeId=" + courseTrainingTypeId +
                '}';
    }
}
