package com.brain.crud.socialclub.mySql;


import com.brain.crud.socialclub.dao.AbstractJDBCDao;
import com.brain.crud.socialclub.dao.UserAuthChecker;
import com.brain.crud.socialclub.exception.PersistException;
import com.brain.crud.socialclub.model.User;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author Gorchakov Vladimir
 * @version 1.0
 */


public class UserDaoSql extends AbstractJDBCDao<User, Long> implements UserAuthChecker {


    public UserDaoSql(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, nickname, username, password, surname, date_of_birth, create_date, last_modify  FROM auto_social_site_base.users ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO auto_social_site_base.users (username, nickname, password, create_date, last_modify) VALUES (?,?,?,?,?)";
    } //todo age

    @Override
    public String getUpdateQuery() {
        return "UPDATE auto_social_site_base.users SET username = ?, nickname = ?, password = ?, surname = ?, date_of_birth = ?, last_modify = ? WHERE id = ? ";
    }

/*    @Override
    public String getSearchQuery(String... statements) {
        int i = statements.length;
        return null;
    }*/

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM auto_social_site_base.users WHERE id = ?";
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                Calendar calendar = null;
                Date date = resultSet.getDate("date_of_birth");
                if (date != null) {
                    calendar = new GregorianCalendar();
                    calendar.setTimeInMillis(date.getTime());
                }
                user.setId(resultSet.getLong("id"));
                user.setNickname(resultSet.getString("nickname"));
                user.setName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSurname(resultSet.getString("surname"));
                user.setDateOfBirth(calendar);
                user.setCreateDate(resultSet.getDate("create_date"));
                user.setLastModify(resultSet.getDate("last_modify"));
                result.add(user);
            }
        } catch (NoSuchElementException noElement) {
            System.out.println("Doesn't have any users");
            return null;
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            Date currentDate = new Date(new java.util.Date().getTime());
            statement.setString(1, object.getName());
            statement.setString(2, object.getNickname());
            statement.setString(3, object.getPassword());
            statement.setDate(4, currentDate);
            statement.setDate(5, currentDate);
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getNickname());
            statement.setString(3, object.getPassword());
            statement.setString(4, object.getSurname());
            Calendar calendar = object.getDateOfBirth();
            if (calendar != null) {
                Date date = new Date(calendar.getTimeInMillis());
                statement.setDate(5, date);
            } else {
                statement.setNull(5, Types.DATE);
            }
            statement.setDate(6, new Date(new java.util.Date().getTime()));
            statement.setLong(7, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public User checkUserAuthDate(String name, String password) throws PersistException {
        String sql = "SELECT * FROM users WHERE nickname = ? AND password = ?";
        List<User> users;
        try (PreparedStatement preparedStatement = super.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            users = parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return users.size() == 0 ? null : users.iterator().next();
    }

}

