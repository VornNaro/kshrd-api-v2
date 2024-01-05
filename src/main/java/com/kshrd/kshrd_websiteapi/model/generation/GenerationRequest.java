package com.kshrd.kshrd_websiteapi.model.generation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class GenerationRequest {

    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "name", position = 1)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ApiModelProperty(value = "start year", position = 2)
    @NotBlank(message = "Start year cannot be empty")
    private String startYear;

    @ApiModelProperty(value = "end year", position = 3)
    @NotBlank(message = "End year cannot be empty")
    private String endYear;

    public GenerationRequest() {
    }


    public GenerationRequest(int id, @NotBlank(message = "Name cannot be empty") String name, @NotBlank(message = "Start year cannot be empty") String startYear, @NotBlank(message = "End year cannot be empty") String endYear) {
        this.id = id;
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
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

    public String getStartYear() {
        return startYear.trim();
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear.trim();
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        return "GenerationRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startYear='" + startYear + '\'' +
                ", endYear='" + endYear + '\'' +
                '}';
    }
}
