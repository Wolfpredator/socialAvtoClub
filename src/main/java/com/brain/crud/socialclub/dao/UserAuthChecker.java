package com.brain.crud.socialclub.dao;

import com.brain.crud.socialclub.exception.PersistException;
import com.brain.crud.socialclub.model.User;

public interface UserAuthChecker {

    User checkUserAuthDate(String name, String password) throws PersistException;

}
