package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    final private Object[] data;
    private int size = 0;

    public SimpleArray(int length) {
        data = new Object[length];
    }

    public void add(T model) {
        int i = Objects.checkIndex(size, data.length);
        size++;
        data[i] = model;
    }

    public void set(int index, T model) {
        int i = Objects.checkIndex(index, size);
        data[i] = model;
    }

    public void remove(int index) {
        int i = Objects.checkIndex(index, size);
        size--;
        System.arraycopy(data, i + 1, data, i, data.length - index - 1);
        data[size] = null;
    }

    public T get(int index) {
        int i = Objects.checkIndex(index, size);
        return (T) data[i];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (T) data[point++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleArray<Integer> arr = new SimpleArray<>(6);
        arr.add(5);
        arr.add(4);
        arr.add(3);
        arr.add(2);
        arr.add(1);

        arr.set(2, 7);
        arr.set(4, 8);

        arr.add(10);
        arr.remove(1);

        arr.add(11);
        arr.remove(3);

        arr.add(9);

        for (Integer ints : arr) {
            System.out.println(ints);
        }

    }
}
