package com.kshrd.kshrd_websiteapi.model.generation;

public class GenerationResponse {

    private int id;
    private String name;
    private String startYear;
    private String endYear;

    public GenerationResponse() {
    }

    public GenerationResponse(int id, String name, String startYear, String endYear) {
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
        return "GenerationResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startYear='" + startYear + '\'' +
                ", endYear='" + endYear + '\'' +
                '}';
    }
}
