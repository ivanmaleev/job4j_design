package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream io = new FileInputStream("even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int read;
            while ((read = io.read()) != -1) {
                stringBuilder.append((char) read);
            }
            String[] lines = stringBuilder.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(Integer.parseInt(line) % 2 == 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}