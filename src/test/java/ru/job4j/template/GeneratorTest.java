package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void produce() {
        Generator gen = new GeneratorQuestions();
        String templ = "I am ${name}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan");
        String result = gen.produce(templ, args);
        assertThat(result, is("I am Ivan"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produceEx() {
        Generator gen = new GeneratorQuestions();
        String templ = "I am ${name}?";
        Map<String, String> args = new HashMap<>();
        args.put("arg1", "Ivan");
        String result = gen.produce(templ, args);
    }
}