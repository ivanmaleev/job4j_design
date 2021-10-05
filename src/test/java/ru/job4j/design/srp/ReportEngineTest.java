package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.*;

public class ReportEngineTest {

    @Test
    public void whenProgGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee<Integer> worker = new Employee<>("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        String str = expect.toString();
        str = str.replace("\n", "<br>");
        str = str.replace("\"", "&quot;");
        assertThat(engine.generate(em -> true), is(str));
    }

    @Test
    public void whenBuhGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee<Double> worker = new Employee<>("Ivan", now, now, 100d);
        store.add(worker);
        Report engine = new ReportBuh(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        String str = expect.toString();
        assertThat(engine.generate(em -> true), is(str));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee<Integer> worker = new Employee<>("Ivan", now, now, 100);
        Employee<Integer> worker2 = new Employee<>("Anton", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        String str = expect.toString();
        assertThat(engine.generate(em -> true), is(str));
    }

}