package com.kshrd.kshrd_websiteapi.model.generation;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GenerationDto {

    private int id;
    private String name;
    private String startYear;
    private String endYear;
    @JsonIgnore
    private boolean status;

    public GenerationDto() {
        this.status = true;
    }

    public GenerationDto(int id, String name, String startYear, String endYear, boolean status) {
        this.id = id;
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
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

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
