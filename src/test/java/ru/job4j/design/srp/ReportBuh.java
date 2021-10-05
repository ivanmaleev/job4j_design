package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportBuh implements Report {

    private final Store store;

    public ReportBuh(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee<? extends Number>> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee<? extends Number> employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
