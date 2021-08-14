package ru.job4j.generics;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest extends TestCase {

    @Test
    public void testAdd() {

        SimpleArray<Integer> in = new SimpleArray<>(5);
        in.add(5);
        in.add(4);
        in.add(3);
        in.add(2);
        in.add(1);

        Iterator<Integer> iterator = in.iterator();
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(1));

    }

    @Test
    public void testSet() {

        SimpleArray<Integer> in = new SimpleArray<>(5);
        in.add(5);
        in.add(4);
        in.add(3);
        in.add(2);
        in.add(1);

        in.set(2, 7);
        in.set(4, 8);

        Iterator<Integer> iterator = in.iterator();
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(8));

    }

    @Test
    public void testRemove() {

        SimpleArray<Integer> in = new SimpleArray<>(5);
        in.add(5);
        in.add(4);
        in.add(3);
        in.add(2);
        in.add(1);

        in.remove(2);
        in.remove(1);

        Iterator<Integer> iterator = in.iterator();
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(1));

    }

    @Test
    public void testGet() {

        SimpleArray<Integer> in = new SimpleArray<>(5);
        in.add(5);
        in.add(4);
        in.add(3);
        in.add(2);
        in.add(1);

        assertThat(in.get(0), is(5));
        assertThat(in.get(1), is(4));
        assertThat(in.get(2), is(3));
        assertThat(in.get(3), is(2));
        assertThat(in.get(4), is(1));

    }

    @Test
    public void testIteratorHasNextFalse() {

        SimpleArray<Integer> in = new SimpleArray<>(5);
        in.add(5);
        in.add(4);
        in.add(3);
        in.add(2);
        in.add(1);

        Iterator<Integer> iterator = in.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}