package com.kshrd.kshrd_websiteapi.model.menu;

public class MenuResponseWithParent {

    private int id;

    private MenuParent menuParent;
    private String menu_order;
    private String name;
    private String link;

    public MenuResponseWithParent() {
    }

    public MenuResponseWithParent(int id, MenuParent menuParent, String menu_order, String name, String link) {
        this.id = id;
        this.menuParent = menuParent;
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

    public MenuParent getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(MenuParent menuParent) {
        this.menuParent = menuParent;
    }

    public String getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(String menu_order) {
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
        return "MenuResponseWithParent{" +
                "id=" + id +
                ", menuParent=" + menuParent +
                ", menu_order='" + menu_order + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
