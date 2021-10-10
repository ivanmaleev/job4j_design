package ru.job4j.design.lsp.parking;

public class Truck extends Automobile {

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return super.getSize();
    }
}
