package com.brain.crud.socialclub.mySql;

import com.brain.crud.socialclub.dao.AbstractJDBCDao;
import com.brain.crud.socialclub.dao.Identified;
import com.brain.crud.socialclub.exception.PersistException;
import com.brain.crud.socialclub.model.FriendRelation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class FriendDaoSql extends AbstractJDBCDao {


    public FriendDaoSql(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM auto_social_site_base.friends WHERE friend_one = ? OR friend_two = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO auto_social_site_base.friends (friend_one, friend_two, ) VALUE (?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE auto_social_site_base.friends SET status = '?' WHERE friend_one IN(?,?) AND friend_two IN(?,?)";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM auto_social_site_base.friends WHERE friend_one IN(?,?) AND friend_two IN(?,?)";
    }

    @Override
    protected List parseResultSet(ResultSet resultSet) throws PersistException {
        FriendRelation relation;
        LinkedList<FriendRelation> result = new LinkedList();
        try {
            while (resultSet.next()) {
                long friend_one = resultSet.getLong("friend_one");
                long friend_two = resultSet.getLong("friend_two");
                int status = resultSet.getInt("status");
                relation = new FriendRelation(friend_one, friend_two, status);
                result.add(relation);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return null;
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement statement, Identified object) throws PersistException {
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, Identified object) throws PersistException {

    }

}
