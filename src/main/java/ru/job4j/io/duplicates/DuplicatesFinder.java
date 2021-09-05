package ru.job4j.io.duplicates;

import ru.job4j.io.ArgsName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = ArgsName.of(args);
        String pathStr = argsName.get("path");
        Path path = Path.of(pathStr);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("is not a directory");
        }
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(path, duplicatesVisitor);
        for (var file : duplicatesVisitor.getDuplicates().entrySet()) {
            System.out.println(file.getKey() + " " + file.getValue().getSize());
        }
    }
}