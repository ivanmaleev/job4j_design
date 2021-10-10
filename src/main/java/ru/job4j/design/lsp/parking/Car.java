package ru.job4j.design.lsp.parking;

public class Car extends Automobile {

    public Car() {
        this.size = 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
