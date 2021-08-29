package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        final DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        for (Map.Entry<Path, FileProperty> file : duplicatesVisitor.getDuplicates().entrySet()) {
            System.out.println(file.getKey() + " " + file.getValue().getSize());
        }
    }
}