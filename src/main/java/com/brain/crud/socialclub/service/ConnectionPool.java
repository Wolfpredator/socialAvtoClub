package com.brain.crud.socialclub.service;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private final SettingsJDBC settingsJDBC;
    private ArrayDeque<Connection> readyConnections;


    private final String DRIVER_NAME;
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;


    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        while (connection == null) {
            connection = readyConnections.poll();
            if (connection == null) {
                createConnection();
            }
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        readyConnections.add(connection);
    }

    private void createConnection() {

        Connection connection;
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            readyConnections.add(connection);
            log.info("Create DB connection. Count connections: " + readyConnections.size());
        } catch (Exception e) {
            log.error("Some problem with create connection", e);
        }
    }

    private ConnectionPool() {
        readyConnections = new ArrayDeque<>();
        settingsJDBC = SettingsJDBC.getInstance();
        DRIVER_NAME = settingsJDBC.value(settingsJDBC.DRIVER_NAME);
        USERNAME = settingsJDBC.value(settingsJDBC.USERNAME);
        PASSWORD = settingsJDBC.value(settingsJDBC.PASSWORD);
        URL = settingsJDBC.value(settingsJDBC.URL);
        for (int i = 0; i < 5; i++) {
            createConnection();
        }
    }

}
