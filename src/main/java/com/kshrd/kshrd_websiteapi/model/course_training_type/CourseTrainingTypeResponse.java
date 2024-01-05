package com.kshrd.kshrd_websiteapi.model.course_training_type;

public class CourseTrainingTypeResponse {

    private int id;
    private String name;

    public CourseTrainingTypeResponse(){

    }

    public CourseTrainingTypeResponse(int id, String name) {
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
        return "CourseTrainingType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
