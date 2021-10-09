package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public abstract class Parking {
    private List<Automobile> automobileList = new ArrayList<>();
    private int numberOfParkingSpaces;
    private int size;

    public void parkAuto(Automobile automobile) {
    }

}
