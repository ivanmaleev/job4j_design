package ru.job4j.io;

import java.io.*;
import java.util.stream.Stream;

public class Analizy {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            String startTime = "";
            String endTime = "";
            while ((line = in.readLine()) != null) {
                String[] str = line.split(" ");
                if (str[0].equals("400")
                        || str[0].equals("500")) {
                    if ("".equals(startTime)) {
                        startTime = str[1];
                    }
                } else {
                    if (!"".equals(startTime) && "".equals(endTime)) {
                        endTime = str[1];
                    }
                    if (!"".equals(startTime) && !"".equals(endTime)) {
                        out.println(startTime.concat(";").concat(endTime));
                        startTime = "";
                        endTime = "";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("ServerLog.txt", "unavailable.csv");
    }
}