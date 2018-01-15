package com.brain.crud.socialclub.service;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsJDBC {
    private static SettingsJDBC instance;
    public final String DRIVER_NAME = "jdbc.driverClassName";
    public final String USERNAME = "jdbc.username";
    public final String PASSWORD = "jdbc.password";
    public final String URL = "jdbc.url";

    private final Properties properties = new Properties();

    private SettingsJDBC() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("database.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SettingsJDBC getInstance() {
        if (instance == null) {
            instance = new SettingsJDBC();
        }
        return instance;
    }

    public String value(String key) {
        return this.properties.getProperty(key);
    }
}
