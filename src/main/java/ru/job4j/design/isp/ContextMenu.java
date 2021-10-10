package ru.job4j.design.isp;

import java.util.List;

public class ContextMenu extends MenuElement {

    public ContextMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuElement> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<MenuElement> childrens) {
        this.childrens = childrens;
    }
}
