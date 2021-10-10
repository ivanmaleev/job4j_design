package ru.job4j.design.lsp.parking;

public class CarParking implements Parking {

    private final int numberOfParkingSpaces;
    private int size;
    Automobile[] carList;
    Automobile[] truckList;
    private int pointCar;
    private int pointTruck;

    public CarParking(int numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
        this.carList = new Automobile[numberOfParkingSpaces];
        this.truckList = new Automobile[numberOfParkingSpaces];
    }

    @Override
    public boolean parkAuto(Automobile automobile) {
        int sizeOfAutomobile = automobile.getSize();
        if (numberOfParkingSpaces >= size + sizeOfAutomobile) {
            if (sizeOfAutomobile == 1) {
                carList[pointCar] = automobile;
                pointCar++;
            } else {
                truckList[pointTruck] = automobile;
                pointTruck++;
            }
            size += sizeOfAutomobile;
            return true;
        }
        return false;
    }
}
