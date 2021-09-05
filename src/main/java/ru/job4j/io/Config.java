package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();
    private List<String> lines;

    public Config(final String path) {
        this.path = path;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            lines = read.lines()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
        Predicate<String> predicateComment = str -> !str.startsWith("#");
        Predicate<String> predicateEmpty = str -> !(str.length() == 0);
        Predicate<String> predicate = predicateEmpty.and(predicateComment);
        values = collectFiles(predicate).stream().collect(Collectors.toMap(str -> str.split("=")[0], str -> str.split("=")[1]));
    }

    private boolean isValid() {
        Predicate<String> searchErrors = (str -> !(str.length() == 0)
                && !str.startsWith("#")
                && (str.split("=").length != 2));
        return collectFiles(searchErrors).size() == 0;
    }

    public String value(String key) {
        return values.get(key);
    }

    public List<String> collectFiles(Predicate<String> filter) {
        return lines.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        lines.stream().forEach(out::add);
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config);

        for (Map.Entry<String, String> entry : config.values.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}