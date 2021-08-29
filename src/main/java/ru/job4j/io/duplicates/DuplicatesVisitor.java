package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<Path, FileProperty> files = new HashMap<>();

    public Map<Path, FileProperty> getDuplicates() {
        Map<Path, FileProperty> duplicates = new HashMap<>();
        for (Map.Entry<Path, FileProperty> fileI : files.entrySet()) {
            for (Map.Entry<Path, FileProperty> fileJ : files.entrySet()) {
                if (fileI.getKey().equals(fileJ.getKey())) {
                    continue;
                }
                if (fileI.getValue().getName().equals(fileJ.getValue().getName())) {
                    duplicates.put(fileJ.getKey(), fileJ.getValue());
                }
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