package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MaxMinTest {

    @Test
    public void maxString() {
        List<String> strings = List.of("a", "b", "z", "c");
        Comparator<String> comp = String::compareTo;
        assertTrue("z".equals(MaxMin.max(strings, comp)));
    }

    @Test
    public void maxInt() {
        List<Integer> ints = List.of(10, 2, 3, 7);
        Comparator<Integer> comp = Integer::compare;
        assertTrue(10 == MaxMin.max(ints, comp));
    }

    @Test
    public void minString() {
        List<String> strings = List.of("a", "b", "z", "c");
        Comparator<String> comp = String::compareTo;
        assertTrue("a".equals(MaxMin.min(strings, comp)));
    }

    @Test
    public void minInt() {
        List<Integer> ints = List.of(10, 2, 3, 7);
        Comparator<Integer> comp = Integer::compare;
        assertTrue(2 == MaxMin.min(ints, comp));
    }
}