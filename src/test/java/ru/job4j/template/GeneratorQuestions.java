package ru.job4j.template;

import java.util.Map;

public class GeneratorQuestions implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        if (args.get("arg1") != null) {
            throw new IllegalArgumentException();
        }
        return "I am Ivan";
    }
}
