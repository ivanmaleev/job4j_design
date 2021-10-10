/*
package ru.job4j.design.ocp;

import org.antlr.v4.runtime.atn.AmbiguityInfo;

import java.util.List;

public class Ocp1 {
    private static class Car {
        public void drive() {
            System.out.println("drive car");
        }
    }

    public static void main(String[] args) {
        List<Car> cars = List.of(new Car());
        cars.forEach(Car::drive);
    }
}

public class Ocp1 {
    private interface Automobiles {
        void drive();
    }

    private static class Car implements Automobiles {
        public void drive() {
            System.out.println("drive car");
        }
    }

    private static class Truck implements Automobiles {
        public void drive() {
            System.out.println("drive truck");
        }
    }

    public static void main(String[] args) {
        List<Automobiles> autos = List.of(new Car(), new Truck());
        autos.forEach(Automobiles::drive);
    }
}

public class Ocp2 {
    private static class Tiger {
        public void eat() {
            System.out.println("drive car");
        }
    }

    public static void main(String[] args) {
        List<Tiger> cars = List.of(new Tiger());
        cars.forEach(Tiger::eat);
    }
}

public class Ocp2 {
    private interface Animals {
        void eat();
    }

    private static class Tiger implements Animals {
        public void eat() {
            System.out.println("Tiger eats");
        }
    }

    private static class Elephant implements Animals {
        public void eat() {
            System.out.println("Elephant Tiger eats");
        }
    }

    public static void main(String[] args) {
        List<Animals> amins = List.of(new Tiger(), new Elephant());
        amins.forEach(Animals::eat);
    }
}

public class Ocp3 {
    private static class JavaDeveloper {
        public void writeCode() {
            System.out.println("Java code");
        }
    }

    public static void main(String[] args) {
        List<JavaDeveloper> javDev = List.of(new JavaDeveloper());
        javDev.forEach(JavaDeveloper::writeCode);
    }
}

public class Ocp3 {
    private interface Developers {
        void writeCode();
    }

    private static class JavaDeveloper implements Developers {
        public void writeCode() {
            System.out.println("Java code");
        }
    }

    private static class CppDeveloper implements Developers {
        public void writeCode() {
            System.out.println("C++ code");
        }
    }

    public static void main(String[] args) {
        List<Developers> devs = List.of(new JavaDeveloper(), new CppDeveloper());
        devs.forEach(Developers::writeCode);
    }
}
*/
