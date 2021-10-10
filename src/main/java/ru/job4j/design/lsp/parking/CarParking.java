package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {

    private int numberOfParkingSpaces;
    private int size;
    List<Automobile> automobileList = new ArrayList<>();

    public CarParking(int numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    @Override
    public boolean parkAuto(Automobile automobile) {
        int sizeOfAutomobile = automobile.getSize();
        if (numberOfParkingSpaces >= size + sizeOfAutomobile) {
            automobileList.add(automobile);
            size += sizeOfAutomobile;
            return true;
        }
        return false;
    }
}
