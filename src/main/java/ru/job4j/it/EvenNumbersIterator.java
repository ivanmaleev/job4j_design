package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (point < data.length && data[point] % 2 != 0) {
            point++;
        }
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext() || point == -1) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
