package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String action = CONTINUE;
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        int randomIndex;
        String randomAnswer;
        while (!action.equals(OUT)) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if (s.equals(OUT)
                    || s.equals(STOP)
                    || s.equals(CONTINUE)) {
                action = s;
            }
            log.add(s);
            if (action.equals(CONTINUE)) {
                randomIndex = (int) (Math.random() * (answers.size() - 1));
                randomAnswer = answers.get(randomIndex);
                System.out.println(randomAnswer);
                log.add(randomAnswer);
            }
        }
        log.add(STOP);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                answers.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String s : log) {
                pw.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("logChat.txt", "answers.txt");
        cc.run();
    }
}