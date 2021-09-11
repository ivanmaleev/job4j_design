package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {
    private int hP;

    public int gethP() {
        return hP;
    }

    public Engine() {
    }

    public Engine(int hP, Car car) {
        this.hP = hP;
    }

    public Engine(int hP) {
        this.hP = hP;
    }
}
