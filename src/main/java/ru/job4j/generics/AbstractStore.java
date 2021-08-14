package ru.job4j.generics;

public class AbstractStore<T extends Base> extends Base implements Store<T> {

    private final Store<T> store = new MemStore<>();

    public AbstractStore(String id) {
        super(id);
    }

    public void add(T model) {
        store.add(model);
    }

    public boolean replace(String id, T model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    public T findById(String id) {
        return (T) store.findById(id);
    }
}
