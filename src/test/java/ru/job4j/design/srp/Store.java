package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    List<Employee<? extends Number>> findBy(Predicate<Employee<? extends Number>> filter);
}