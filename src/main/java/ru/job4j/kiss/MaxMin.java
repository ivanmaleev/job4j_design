package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public static <T> T max(List<T> value, Comparator<T> comparator) {
        T result = null;
        if (value.size() == 1) {
            return value.get(0);
        } else if (value.size() > 1) {
            result = value.get(0);
            for (int i = 1; i < value.size(); i++) {
                result = comparator.compare(result, value.get(i)) > 0
                        ? result : value.get(i);
            }
        }
        return result;
    }

    public static <T> T min(List<T> value, Comparator<T> comparator) {
        return max(value, comparator.reversed());
    }
}