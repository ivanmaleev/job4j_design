package ru.job4j.design.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

public class ParkingTest {

    @Test
    public void whenParkCar() {
        Automobile truck = new Truck();
        Parking parking = new CarParking();
        Assert.assertTrue(parking.parkAuto(truck));
    }
}