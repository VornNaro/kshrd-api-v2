package com.kshrd.kshrd_websiteapi.model.course_training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeId;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CourseTrainingRequest {

    @ApiModelProperty(value = "name", position = 1)
    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "name", position = 2)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ApiModelProperty(value = "description", position = 3)
    @NotBlank(message = "Description cannot be empty")
    private String description;

    @ApiModelProperty(value = "logo", position = 4)
//    @NotBlank(message = "Logo cannot be empty")
    private String logo;

    @ApiModelProperty(value = "file", position = 5)
    @NotBlank(message = "File cannot be empty")
    private String file;

    @ApiModelProperty(value = "courseTrainingTypeId", position = 6)
    private CourseTrainingTypeId courseTrainingTypeId;

    public CourseTrainingRequest(){

    }

    public CourseTrainingRequest(int id, String name, String description, String logo, String file, CourseTrainingTypeId courseTrainingTypeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.file = file;
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

    public String getDescription() {
        return description.trim();
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
        return file.trim();
    }

    public void setFile(String file) {
        this.file = file;
    }

    public CourseTrainingTypeId getCourseTrainingTypeId() {
        return courseTrainingTypeId;
    }

    public void setCourseTrainingTypeId(CourseTrainingTypeId courseTrainingTypeId) {
        this.courseTrainingTypeId = courseTrainingTypeId;
    }
}
