package ru.job4j.design.lsp.parking;

public class CarParking implements Parking {

    Automobile[] carSpacesList;
    Automobile[] truckSpacesList;
    private int pointCar;
    private int pointTruck;
    private int carCounter;
    private int truckCounter;

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
            truckCounter++;
            return true;
        } else if ((pointCar + sizeOfAutomobile) <= carSpacesList.length) {
            if (sizeOfAutomobile == 1) {
                carSpacesList[pointCar] = automobile;
                carCounter++;
                pointCar++;
            } else {
                for (int i = 0; i < sizeOfAutomobile; i++) {
                    carSpacesList[pointCar] = automobile;
                    pointCar++;
                }
                truckCounter++;
            }
            return true;
        }
        return false;
    }
}
