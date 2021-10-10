package ru.job4j.design.isp;

import java.util.List;

public class ElementJob extends MenuElement {

    Action action;

    public ElementJob(String name) {
        this.name = name;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Action getAction() {
        return action;
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
