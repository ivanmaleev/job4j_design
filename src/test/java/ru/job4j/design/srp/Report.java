package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee<? extends Number>> filter
            , Comparator<Employee<? extends Number>> comparator
            , String format, List<String> columns);
}