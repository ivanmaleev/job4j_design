package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {
    private int hP;
    private Car car;

    public int gethP() {
        return hP;
    }

    @JSONPropertyIgnore
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Engine() {
    }

    public Engine(int hP, Car car) {
        this.hP = hP;
        this.car = car;
    }

    public Engine(int hP) {
        this.hP = hP;
    }
}
