package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var users = store.findBy(filter);
        var lib = new GsonBuilder().create();
        return lib.toJson(users);
    }
}
