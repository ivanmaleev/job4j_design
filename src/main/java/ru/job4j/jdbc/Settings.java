package ru.job4j.jdbc;

import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private final Properties properties = new Properties();

    public void load(InputStream in) {
        try {
            this.properties.load(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }
}
