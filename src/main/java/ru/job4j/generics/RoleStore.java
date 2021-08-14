package ru.job4j.generics;

public class RoleStore extends AbstractStore<Role> {

    private final Store<Role> store = new MemStore<>();

    public RoleStore(String id) {
        super(id);
    }
}
