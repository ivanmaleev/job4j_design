package ru.job4j.design.lsp.parking;

public class CarParking implements Parking {

    Automobile[] carSpacesList;
    Automobile[] truckSpacesList;
    private int pointCar;
    private int pointTruck;

    public CarParking(int numberOfTruckSpaces, int numberOfCarSpaces) {
        this.truckSpacesList = new Automobile[numberOfTruckSpaces];
        this.carSpacesList = new Automobile[numberOfCarSpaces];
    }

    @Override
    public boolean parkAuto(Automobile automobile) {
        int sizeOfAutomobile = automobile.getSize();
        if (sizeOfAutomobile != 1
                && (pointTruck + 1) <= truckSpacesList.length) {
            truckSpacesList[pointTruck] = automobile;
            pointTruck++;
            return true;
        } else if ((pointCar + sizeOfAutomobile) <= carSpacesList.length) {
            carSpacesList[pointCar] = automobile;
            pointCar++;
            return true;
        }
        return false;
    }
}
