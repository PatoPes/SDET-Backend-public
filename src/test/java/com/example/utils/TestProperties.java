package com.example.utils;

import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private static TestProperties instance;

    private final Properties properties;

    private TestProperties() {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (NullPointerException | IOException ignored) {
        }
    }

    public static synchronized TestProperties getInstance() {
        if (instance == null) {
            instance = new TestProperties();
        }
        return instance;
    }

    public int getInteger(String propertyName) {
        return Integer.parseInt(getString(propertyName));
    }

    public String getString(String propertyName, String defaultValue) {
        String value = properties.getProperty(propertyName);
        if (null != value) {
            return value;
        }
        return defaultValue;
    }

    public String getString(String propertyName) {
        return getString(propertyName, null);
    }

}
