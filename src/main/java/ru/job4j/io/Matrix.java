package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    static String multiple(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Integer value = (i + 1) * (j + 1);
                String strValue = value.toString();
                stringBuilder.append(strValue.length() == 1
                        ? " ".concat(strValue).concat(" ") : strValue.concat(" "));
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        String str = Matrix.multiple(9);

        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            String[] lines = str.split(System.lineSeparator());
            for (String line : lines) {
                out.write(line.getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}