package ru.job4j.io.searchfiles;

import ru.job4j.io.ArgsName;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;
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
        final Predicate<Path> pred = searchPredicate(type, n);
        SearchFiles searcher = new SearchFiles(n, type, pred);
        Files.walkFileTree(pathIn, searcher);
        saveLog(log, searcher.getFiles());
    }

    private static void saveLog(String log, Set<Path> files) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(log))) {
            for (Path file : files) {
                out.println(file.toAbsolutePath());
            }
        }
    }

    private static Predicate<Path> searchPredicate(String type, String n) {
        final Predicate<Path> pred;
        final String regexp;
        if (type.equals("name")) {
            regexp = n;
            pred = (path) -> path.getFileName().toString().equals(regexp);
        } else {
            if (type.equals("mask")) {
                n = n.replaceAll("[*]{1}", "[a-zA-Z_0-9]{0,}");
                n = n.replaceAll("[\\.]{1}", "\\\\.");
                n = n.replaceAll("\\?", "[0-9]{1}");
                regexp = n;
            } else {
                regexp = n;
            }
            pred = (path) -> {
                Pattern p = Pattern.compile(regexp);
                Matcher matcher = p.matcher(path.getFileName().toString());
                return matcher.matches();
            };
        }
        return pred;
    }
}

class SearchFiles extends SimpleFileVisitor<Path> {

    final private String n;
    final private String type;
    final private Set<Path> files = new HashSet<>();
    final private Predicate<Path> pred;

    public Set<Path> getFiles() {
        return files;
    }

    public SearchFiles(String n, String type, Predicate<Path> pred) {
        this.n = n;
        this.type = type;
        this.pred = pred;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pred.test(file)) {
            files.add(file);
        }
        return super.visitFile(file, attrs);
    }
}