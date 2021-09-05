package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {

    private String produser;
    private String model;
    private String[] drivers;
    private int maxSpeed;
    Engine engine;

    public Car(String produser, String model, String[] drivers, int maxSpeed, Engine engine) {
        this.produser = produser;
        this.model = model;
        this.drivers = drivers;
        this.maxSpeed = maxSpeed;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{"
                + "produser='" + produser + '\''
                + ", model='" + model + '\''
                + ", drivers=" + Arrays.toString(drivers)
                + ", maxSpeed=" + maxSpeed
                + ", engine=" + engine
                + '}';
    }

    public static class Engine {
        private int hP;

        public Engine(int hP) {
            this.hP = hP;
        }
    }

    public static void main(String[] args) {
        String[] drivers = new String[3];
        drivers[0] = "Ivan";
        drivers[1] = "Anton";
        drivers[2] = "Valeriy";
        Car car = new Car("LADA", "Vesta", drivers, 180, new Car.Engine(120));

        final Gson gson = new GsonBuilder().create();
        String carJson = gson.toJson(car);
        System.out.println(carJson);

        Car carFromJson = gson.fromJson(carJson, Car.class);
        System.out.println(carFromJson);
    }
}
