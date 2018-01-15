package com.brain.crud.socialclub.dao;

import com.brain.crud.socialclub.exception.PersistException;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {


    public T create(T object) throws PersistException;

    public T getByPK(PK key) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

    public List<T> getAll() throws PersistException;

    //public T search(String... statements) throws PersistException;

}
