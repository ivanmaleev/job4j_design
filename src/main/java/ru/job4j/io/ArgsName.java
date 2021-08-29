package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        final String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    private void parse(String[] args) {
        values.clear();
        for (String arg : args) {
            final String[] split = arg.split("=");
            if (!arg.startsWith("-") || (split.length != 2)) {
                throw new IllegalArgumentException();
            }
            values.put(split[0].substring(1), split[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}