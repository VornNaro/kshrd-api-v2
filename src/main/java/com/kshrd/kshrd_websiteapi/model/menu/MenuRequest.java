package com.kshrd.kshrd_websiteapi.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class MenuRequest {

    @JsonIgnore
    private int id;

    @ApiModelProperty(value = "parent id", position = 1)
    private int parent_id;

    @ApiModelProperty(value = "menu order", position = 2)
    private int menu_order;

    @ApiModelProperty(value = "name", position = 3)
    @NotBlank(message = "Name order cannot be empty")
    private String name;

    @ApiModelProperty(value = "link", position = 4)
    @NotBlank(message = "Link cannot be empty")
    private String link;

    public MenuRequest() {
    }

    public MenuRequest(int id, int parent_id, int menu_order, @NotBlank(message = "Name order cannot be empty") String name, @NotBlank(message = "Link cannot be empty") String link) {
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
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link.trim();
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "MenuRequest{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", menu_order=" + menu_order +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
