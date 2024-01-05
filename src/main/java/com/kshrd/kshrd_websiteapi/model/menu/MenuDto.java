package com.kshrd.kshrd_websiteapi.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MenuDto {

    private int id;
    private int parent_id;
    private int menu_order;
    private String name;
    private String link;
    @JsonIgnore
    private boolean status;

    public MenuDto() {
    }

    public MenuDto(int id, int parent_id, int menu_order, String name, String link, boolean status) {
        this.id = id;
        this.parent_id = parent_id;
        this.menu_order = menu_order;
        this.name = name;
        this.link = link;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(int menu_order) {
        this.menu_order = menu_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", menu_order=" + menu_order +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", status=" + status +
                '}';
    }
}
