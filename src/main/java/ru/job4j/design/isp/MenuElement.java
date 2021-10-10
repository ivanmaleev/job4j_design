package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuElement {

    String name;
    List<MenuElement> childrens = new ArrayList<>();
    Action action;

    public void setChildrens(List<MenuElement> childrens) {
        this.childrens = childrens;
    }

    public List<MenuElement> getChildrens() {
        return childrens;
    }

    public String getName() {
        return name;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }
}
