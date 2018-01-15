package com.brain.crud.socialclub.mySql;

import com.brain.crud.socialclub.service.ConnectionPoolService;

import java.sql.Connection;

class FriendDaoSqlTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void getByPK() {
        try (ConnectionPoolService connectionPoolService = new ConnectionPoolService()) {
            Connection connection = connectionPoolService.getConnection();
            FriendDaoSql friendDaoSql = new FriendDaoSql(connection);

        } catch (Exception e) {

        }
    }

}