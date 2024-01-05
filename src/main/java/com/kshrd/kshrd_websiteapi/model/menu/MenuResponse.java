package com.kshrd.kshrd_websiteapi.model.menu;

import java.util.List;

public class MenuResponse {

    private int id;
    private List<SubMenu> parent_id;
    private int menu_order;
    private String name;
    private String link;

    public MenuResponse() {
    }

    public MenuResponse(int id, List<SubMenu> parent_id, int menu_order, String name, String link) {
        this.id = id;
        this.parent_id = parent_id;
        this.menu_order = menu_order;
        this.name = name;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SubMenu> getParent_id() {
        return parent_id;
    }

    public void setParent_id(List<SubMenu> parent_id) {
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

    @Override
    public String toString() {
        return "MenuResponse{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", menu_order=" + menu_order +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
