package ru.job4j.serialization.json;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private String produser;
    @XmlAttribute
    private String model;
    @XmlElementWrapper(name = "drivers")
    @XmlElement(name = "driver")
    private String[] drivers;
    @XmlAttribute
    private int maxSpeed;
    Engine engine;

    public Car() {
    }

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

    @XmlRootElement(name = "engine")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Engine {
        private int hP;

        public Engine() {
        }

        public Engine(int hP) {
            this.hP = hP;
        }
    }

    public static void main(String[] args) throws JAXBException {
        String[] drivers = new String[3];
        drivers[0] = "Ivan";
        drivers[1] = "Anton";
        drivers[2] = "Valeriy";
        Car car = new Car("LADA", "Vesta", drivers, 180, new Car.Engine(120));

//        final Gson gson = new GsonBuilder().create();
//        String carJson = gson.toJson(car);
//        System.out.println(carJson);
//
//        Car carFromJson = gson.fromJson(carJson, Car.class);
//        System.out.println(carFromJson);

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
