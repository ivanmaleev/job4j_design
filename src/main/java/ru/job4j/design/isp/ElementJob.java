package ru.job4j.design.isp;

import java.util.List;

public class ElementJob extends MenuElement {

    @Override
    void action() {
        System.out.println("Doing job...");
    }

    public ElementJob(String name) {
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
