package ru.job4j.design.isp;

public class SimpleAction implements Action {
    @Override
    public void doAction() {
        System.out.println("Do something");
    }
}
