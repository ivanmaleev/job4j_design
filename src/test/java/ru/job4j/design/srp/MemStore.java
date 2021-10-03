package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private final List<Employee<? extends Number>> employees = new ArrayList<>();

    public void add(Employee<? extends Number> em) {
        employees.add(em);
    }

    @Override
    public List<Employee<? extends Number>> findBy(Predicate<Employee<? extends Number>> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}