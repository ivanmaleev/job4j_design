package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] data;
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
        int i = Objects.checkIndex(index, data.length);
        data[i] = model;
    }

    public void remove(int index) {
        int i = Objects.checkIndex(index, data.length);
        size--;
        System.arraycopy(data, i + 1, data, i, size - i);
        data[size] = null;
    }

    public T get(int index) {
        int i = Objects.checkIndex(index, data.length);
        return (T) data[i];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < data.length && data[point] != null;
            }

            @Override
            public T next() {
                return (T) data[point++];
            }
        };
        return it;
    }

    public static void main(String[] args) {
        SimpleArray<Integer> arr = new SimpleArray<>(6);
        arr.add(5); //0
        arr.add(4); //1
        arr.add(3); //2
        arr.add(2); //3
        arr.add(1); //4

        arr.set(2, 7);  //5, 4, 7, 2, 1
        arr.set(4, 8);  //5, 4, 7, 2, 8

        arr.add(10); //5, 4, 7, 2, 8, 10
        arr.remove(1); //5, 7, 2, 8, 10

        arr.add(11); //5, 7, 2, 8, 10, 11
        arr.remove(3); //5, 7, 2, 10, 11

        arr.add(9); //5, 7, 2, 10, 11, 9

//        System.out.println(arr.get(0));
//        System.out.println(arr.get(1));
//        System.out.println(arr.get(2));
//        System.out.println(arr.get(3));
//        System.out.println(arr.get(4));
//        System.out.println(arr.get(5));

        for (Integer ints : arr) {
            System.out.println(ints);
        }

    }
}
