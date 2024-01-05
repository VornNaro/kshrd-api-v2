package com.kshrd.kshrd_websiteapi.model.generation;

public class GenerationId {
    private int id;

    public GenerationId() {
    }

    public GenerationId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GenerationId{" +
                "id=" + id +
                '}';
    }
}
