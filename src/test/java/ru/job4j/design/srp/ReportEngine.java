package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee<? extends Number>> filter,
                           Comparator<Employee<? extends Number>> comparator,
                           String format, List<String> columns) {
        StringBuilder text = new StringBuilder();
        for (String column : columns) {
            text.append(column).append("; ");
        }
        text.append(System.lineSeparator());
        for (Employee<? extends Number> employee : store.findBy(filter)) {
            for (String column : columns) {
                switch (column) {
                    case "Name":
                        text.append(employee.getName()).append(";");
                        break;
                    case "Hired":
                        text.append(employee.getHired()).append(";");
                        break;
                    case "Fired":
                        text.append(employee.getFired()).append(";");
                        break;
                    case "Salary":
                        text.append(employee.getSalary()).append(";");
                        break;
                    default:
                        break;
                }
            }
            text.append(System.lineSeparator());
        }
        switch (format) {
            case "text":
                return text.toString();
            case "html":
                return txtToHTML(text.toString());
            default:
                break;
        }
        return null;
    }

    private String txtToHTML(String s) {
        StringBuilder builder = new StringBuilder();
        boolean previousWasASpace = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (previousWasASpace) {
                    builder.append("&nbsp;");
                    previousWasASpace = false;
                    continue;
                }
                previousWasASpace = true;
            } else {
                previousWasASpace = false;
            }
            switch (c) {
                case '<':
                    builder.append("&lt;");
                    break;
                case '>':
                    builder.append("&gt;");
                    break;
                case '&':
                    builder.append("&amp;");
                    break;
                case '"':
                    builder.append("&quot;");
                    break;
                case '\n':
                    builder.append("<br>");
                    break;
                case '\t':
                    builder.append("&nbsp; &nbsp; &nbsp;");
                    break;
                default:
                    if (c < 128) {
                        builder.append(c);
                    } else {
                        builder.append("&#").append((int) c).append(";");
                    }
            }
        }
        return builder.toString();
    }
}