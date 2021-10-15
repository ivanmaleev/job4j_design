package ru.job4j.design.lsp.parking;

import org.junit.Assert;
import org.junit.Test;

public class ParkingTest {

    @Test
    public void whenParkCar() {
        Automobile truck = new Truck(2);
        Parking parking = new CarParking(2, 1);
        Assert.assertTrue(parking.parkAuto(truck));
    }

    @Test
    public void whenEmpty() {
        Automobile truck2 = new Truck(2);
        Automobile car = new Car();
        Automobile car2 = new Car();
        Parking parking = new CarParking(1, 1);
        parking.parkAuto(truck2);
        parking.parkAuto(car);
        Assert.assertFalse(parking.parkAuto(car2));
    }

    @Test
    public void whenTruckOnCarSpace() {
        Automobile truck1 = new Truck(2);
        Automobile truck2 = new Truck(2);
        Automobile truck3 = new Truck(3);
        Automobile car = new Car();
        Automobile car2 = new Car();
        Parking parking = new CarParking(1, 6);
        parking.parkAuto(truck1);
        parking.parkAuto(car);
        Assert.assertTrue(parking.parkAuto(truck2));
        Assert.assertTrue(parking.parkAuto(truck3));
        Assert.assertFalse(parking.parkAuto(car2));
    }
}
