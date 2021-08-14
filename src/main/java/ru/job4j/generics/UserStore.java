package ru.job4j.generics;

public class UserStore extends AbstractStore<User> {

    private final Store<User> store = new MemStore<>();

    public UserStore(String id) {
        super(id);
    }
}