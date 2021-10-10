package ru.job4j.design.lsp.parking;

public class CarParking implements Parking {
    @Override
    public boolean parkAuto(Automobile automobile) {
        return true;
    }
}
