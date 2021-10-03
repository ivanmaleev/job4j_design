package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee<Double> worker = new Employee<>("Ivan", now, now, 100d);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        Comparator<Employee<? extends Number>> comparator = Comparator.comparing(Employee::getName);
        String format = "text";
        List<String> columns = List.of("Name", "Hired", "Fired", "Salary");
        assertThat(engine.generate(em -> true, comparator, format, columns), is(expect.toString()));
    }

    @Test
    public void whenNewGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee<Integer> worker = new Employee<>("Ivan", now, now, 200);
        Employee<Integer> worker2 = new Employee<>("Anton", now, now, 100);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary; ")
                .append("\r<br>")
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append("\r<br>")
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append("\r<br>");
        Comparator<Employee<? extends Number>> comparator = (a, b) -> b.getSalary().compareTo(a.getSalary());
        String format = "html";
        List<String> columns = List.of("Name", "Salary");
        assertThat(engine.generate(em -> true, comparator, format, columns), is(expect.toString()));
    }
}