package ru.job4j.io.searchfiles;

import ru.job4j.io.ArgsName;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Illegal parameters, use -d for directory,"
                    + " -n for filename, mask or regexp, -t for type, -o for logname");
        }
        ArgsName argsName = ArgsName.of(args);
        String dir = argsName.get("d");
        String n = argsName.get("n");
        String type = argsName.get("t");
        String log = argsName.get("o");
        Path pathIn = Path.of(dir);
        if (!Files.isDirectory(pathIn)) {
            throw new IllegalArgumentException(dir + "is not a directory");
        }
        if (!type.equals("mask")
                && !type.equals("name")
                && !type.equals("regex")) {
            throw new IllegalArgumentException(type + "wrong type, use mask, name or regex");
        }
        if ("".equals(n)) {
            throw new IllegalArgumentException(type + "wrong filename, mask or regex");
        }
        SearchFiles searcher = new SearchFiles(n, type);
        Files.walkFileTree(pathIn, searcher);
        Set<Path> files = searcher.getFiles();
        try (PrintWriter out = new PrintWriter(new FileOutputStream(log))) {
            for (Path file : files) {
                out.println(file.toAbsolutePath());
            }
        }
    }
}

class SearchFiles extends SimpleFileVisitor<Path> {

    final private String n;
    final private String type;
    final private Set<Path> files = new HashSet<>();

    public Set<Path> getFiles() {
        return files;
    }

    public SearchFiles(String n, String type) {
        this.n = n;
        this.type = type;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        if (type.equals("name") && fileName.equals(n)) {
            files.add(file);
        } else if (type.equals("regex")) {
            Pattern p = Pattern.compile(n);
            Matcher matcher = p.matcher(fileName);
            boolean matches = matcher.matches();
            if (matches) {
                files.add(file);
            }
        } else if (fileName.contains(n)) {
            files.add(file);
        }
        return super.visitFile(file, attrs);
    }

}
