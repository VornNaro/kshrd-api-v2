package com.kshrd.kshrd_websiteapi.model.course_training_type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CourseTrainingTypeRequest {

    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "name", position = 1)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    public CourseTrainingTypeRequest(){

    }

    public CourseTrainingTypeRequest(int id, @NotBlank(message = "Name cannot be empty") String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "CourseTrainingTypeRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
