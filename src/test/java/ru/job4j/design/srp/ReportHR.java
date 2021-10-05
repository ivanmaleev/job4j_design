package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportHR implements Report {

    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee<? extends Number>> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        List<Employee<? extends Number>> employees = store.findBy(filter)
                .stream()
                .sorted((a, b) -> b.getSalary().compareTo(a.getSalary()))
                .collect(Collectors.toList());
        for (Employee<? extends Number> employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
