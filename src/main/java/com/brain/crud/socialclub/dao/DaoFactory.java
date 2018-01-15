package com.brain.crud.socialclub.dao;

import com.brain.crud.socialclub.exception.PersistException;

public interface DaoFactory<MethodConnection> {


    public interface DaoCreator<MethodConnection> {
        public GenericDao create(MethodConnection connection);
    }

    public GenericDao getDao(MethodConnection methodConnection, Class modelObject) throws PersistException;
}
