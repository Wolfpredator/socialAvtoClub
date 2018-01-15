package com.brain.crud.socialclub.mySql;

import com.brain.crud.socialclub.dao.DaoFactory;
import com.brain.crud.socialclub.dao.GenericDao;
import com.brain.crud.socialclub.exception.PersistException;
import com.brain.crud.socialclub.model.User;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private Map<Class, DaoCreator> creators;


    public MySqlDaoFactory() {
        creators = new HashMap<>();
        creators.put(User.class, (DaoCreator<Connection>) UserDaoSql::new);
    }

    @Override
    public GenericDao getDao(Connection connection, Class modelObject) throws PersistException {
        DaoCreator creator = creators.get(modelObject);
        if (creator == null) throw new PersistException("Dao object for " + modelObject + " not found");
        return creator.create(connection);
    }
}




