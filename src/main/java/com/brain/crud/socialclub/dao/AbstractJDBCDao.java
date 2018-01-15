package com.brain.crud.socialclub.dao;

import com.brain.crud.socialclub.exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class AbstractJDBCDao<T extends Identified<PK>, PK extends Long> implements GenericDao<T, PK> {

    protected Connection connection;

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    //  public abstract String getSearchQuery(String... statements);


    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;


    protected abstract void preparedStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    protected abstract void preparedStatementForUpdate(PreparedStatement statement, T object) throws PersistException;


    @Override
    public T create(T object) throws PersistException {
        if (object.getId() != null) {
            throw new PersistException("Object is already create.");
        }
        String sql = getCreateQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementForInsert(preparedStatement, object);
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new PersistException("Persis modify more then 1 object: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

        sql = getSelectQuery() + "WHERE id = LAST_INSERT_ID()";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> list = parseResultSet(resultSet);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Received list null or more then 1");
            }
            object = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return object;
    }

    @Override
    public T getByPK(Long key) throws PersistException {
        List<T> list;
        String sql = getSelectQuery() + "WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementForUpdate(preparedStatement, object);
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try {
                preparedStatement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }

/*    @Override
    public T search(String... statements) throws PersistException {

        String sql = getSearchQuery(statements);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (Exception e){
            throw new PersistException(e);
        }

        return null;
    }*/

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }
}
