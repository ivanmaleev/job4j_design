package ru.job4j.design.srp;

import javax.xml.bind.JAXBException;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);

    String generateXML(Predicate<Employee> filter) throws JAXBException;

    String generateJSON(Predicate<Employee> filter);
}