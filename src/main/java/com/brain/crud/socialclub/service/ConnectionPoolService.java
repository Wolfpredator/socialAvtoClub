package com.brain.crud.socialclub.service;

import java.sql.Connection;

public class ConnectionPoolService implements AutoCloseable {

    private Connection connection;
    private ConnectionPool connectionPool;

    public ConnectionPoolService() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }


    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {

        connectionPool.returnConnection(connection);
    }
}
