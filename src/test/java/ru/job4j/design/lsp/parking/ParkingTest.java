package ru.job4j.design.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

public class ParkingTest {

    @Test
    public void whenParkCar() {
        Automobile truck = new Truck(2);
        Parking parking = new CarParking(10);
        Assert.assertTrue(parking.parkAuto(truck));
    }

    @Test
    public void whenEmpty() {
        Automobile truck2 = new Truck(2);
        Automobile truck3 = new Truck(3);
        Automobile car = new Car();
        Parking parking = new CarParking(5);
        parking.parkAuto(truck2);
        parking.parkAuto(truck3);
        Assert.assertFalse(parking.parkAuto(car));
    }
}
