package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    private Path pathIn;
    private boolean stdout = false;
    private Path pathOut;

    public boolean readCSV(String inPathStr, String delimiter, String outPathStr, String filter) throws IOException {
        validateParameters(inPathStr, outPathStr);
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
                    columns = fillColumns(splitLine, filters, columnNum);
                    firstLine = false;
                    continue;
                }
                for (String columnStr : splitLine) {
                    if (columns.contains(columnNum)) {
                        printOut(stdout, columnStr, out, false);
                    }
                    columnNum++;
                }
                printOut(stdout, "", out, true);
            }
        }
        return true;
    }

    private void printOut(boolean stdout, String columnStr, PrintWriter out, boolean emptyLine) {
        if (emptyLine) {
            if (stdout) {
                System.out.print(columnStr + " ");
            } else {
                out.print(columnStr + " ");
            }
        } else {
            if (stdout) {
                System.out.print(System.lineSeparator());
            } else {
                out.print(System.lineSeparator());
            }
        }
    }

    private List<Integer> fillColumns(String[] splitLine, Set<String> filters, int columnNum) {
        List<Integer> columns = new ArrayList<>();
        for (String columnStr : splitLine) {
            if (filters.contains(columnStr)) {
                columns.add(columnNum);
            }
            columnNum++;
        }
        return columns;
    }


    private void validateParameters(String inPathStr, String outPathStr) {
        pathIn = Path.of(inPathStr);
        if (Files.isDirectory(pathIn)) {
            throw new IllegalArgumentException(inPathStr + " is not a file");
        }
        stdout = false;
        if (outPathStr.equals("stdout")) {
            stdout = true;
        } else {
            pathOut = Path.of(outPathStr);
            if (Files.isDirectory(pathOut)) {
                throw new IllegalArgumentException(outPathStr + " is not a file");
            }
        }
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
        CSVReader csvReader = new CSVReader();
        csvReader.validateParameters(inPathStr, outPathStr);
        csvReader.readCSV(inPathStr, delimiter, outPathStr, filter);
    }
}
