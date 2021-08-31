package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<Path, FileProperty> files = new HashMap<>();

    public Map<Path, FileProperty> getDuplicates() {
        Map<Path, FileProperty> duplicates = new HashMap<>();
        for (var file : files.entrySet()) {
            if ((Collections.frequency(files.values(), file.getValue())) > 1) {
                duplicates.put(file.getKey(), file.getValue());
            }
        }
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        files.put(file.toAbsolutePath(), new FileProperty(attrs.size(), file.toFile().getName()));
        return super.visitFile(file, attrs);
    }
}