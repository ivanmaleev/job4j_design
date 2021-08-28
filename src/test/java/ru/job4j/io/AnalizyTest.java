package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("ServerLog.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("hello foolish dude");
            out.println("java job4j php");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("ServerLog.txt", target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(str -> rsl.append(str).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01" + System.lineSeparator()
                + "11:01:02;11:05:02" + System.lineSeparator()
                + "11:06:02;11:07:02" + System.lineSeparator()));
    }
}