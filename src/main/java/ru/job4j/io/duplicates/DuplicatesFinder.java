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
        String path = argsName.get("path");
        final DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(path), duplicatesVisitor);
        for (var file : duplicatesVisitor.getDuplicates().entrySet()) {
            System.out.println(file.getKey() + " " + file.getValue().getSize());
        }
    }
}