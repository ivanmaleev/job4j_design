package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Predicate<String> predicateComment = str -> !str.startsWith("#");
            Predicate<String> predicateEmpty = str -> !(str.length() == 0);
            Predicate<String> predicate = predicateEmpty.and(predicateComment);
            values = read.lines()
                    .filter(predicate)
                    .collect(Collectors.toMap(str -> str.split("=")[0], str -> str.split("=")[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValid() {
        List<String> errors = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Predicate<String> searchErrors = (str -> !(str.length() == 0)
                                            && !str.startsWith("#")
                                            && (str.split("=").length != 2));

            errors = read.lines()
                    .filter(searchErrors)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return errors.size() == 0;
    }

    public String value(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
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