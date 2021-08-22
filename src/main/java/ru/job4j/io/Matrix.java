package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    static int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }

    public static void main(String[] args) {

        int[][] table = Matrix.multiple(9);

        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] x : table) {
                for (int y : x) {
                    String s = String.valueOf(y);
                    s = s.length() == 1 ? " ".concat(s).concat(" ") : s.concat(" ");
                    out.write(s.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}