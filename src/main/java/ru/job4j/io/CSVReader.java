package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static boolean readCSV(String inPathStr, String delimiter, String outPathStr, String filter) throws IOException {
        Path pathIn = Path.of(inPathStr);
        if (Files.isDirectory(pathIn)) {
            System.out.println(inPathStr + " is not a file");
            return false;
        }
        boolean stdout = false;
        if (outPathStr.equals("stdout")) {
            stdout = true;
        } else {
            Path pathOut = Path.of(outPathStr);
            if (Files.isDirectory(pathOut)) {
                System.out.println(outPathStr + " is not a file");
                return false;
            }
        }
        Set<String> filters = new HashSet<>(Arrays.asList(filter.split(",")));
        boolean firstLine = true;
        List<Integer> columns = new ArrayList<>();
        try (var scanner = new Scanner(pathIn);
             PrintWriter out = (stdout ? null : (new PrintWriter(new FileOutputStream(outPathStr))))) {
            String line;
            String[] splitLine;
            while (scanner.hasNextLine()) {
                int columnNum = 0;
                line = scanner.nextLine();
                splitLine = line.split(delimiter);
                if (firstLine) {
                    for (String columnStr : splitLine) {
                        if (filters.contains(columnStr)) {
                            columns.add(columnNum);
                        }
                        columnNum++;
                    }
                    firstLine = false;
                    continue;
                }
                for (String columnStr : splitLine) {
                    if (columns.contains(columnNum)) {
                        if (stdout) {
                            System.out.println(columnStr);
                        } else {
                            out.println(columnStr);
                        }
                    }
                    columnNum++;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        String inPathStr = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String outPathStr = argsName.get("out");
        String filter = argsName.get("filter");
        readCSV(inPathStr, delimiter, outPathStr, filter);
    }
}
