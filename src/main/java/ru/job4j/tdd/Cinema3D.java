package ru.job4j.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cinema3D implements Cinema {

    private List<Session> sessions = new ArrayList<>();

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return sessions.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return new Ticket3D(row, column);
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}
