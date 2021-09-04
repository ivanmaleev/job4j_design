package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteN = 4;
        short shortN = 100;
        int intN = 1000;
        char c = 'g';
        long longN = 500;
        float floatN = 50.6f;
        double doubleN = 50.6d;
        boolean bool = true;

        LOG.debug("byte: {}, short: {}, int: {}, char: {}, long: {}, float: {}, double: {}, boolean: {}",
                byteN, shortN, intN, c, longN, floatN, doubleN, bool);
    }
}