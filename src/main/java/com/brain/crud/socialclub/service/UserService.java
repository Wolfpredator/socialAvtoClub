package com.brain.crud.socialclub.service;


import com.brain.crud.socialclub.dao.GenericDao;
import com.brain.crud.socialclub.dao.UserAuthChecker;
import com.brain.crud.socialclub.model.User;
import com.brain.crud.socialclub.mySql.MySqlDaoFactory;
import com.brain.crud.socialclub.mySql.UserDaoSql;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    private User user;
    private MessageDigest messageDigest;
    private MySqlDaoFactory mySqlDaoFactory;
    private GenericDao dao;
    private UserAuthChecker checker;

    private static final Logger log = Logger.getLogger("UserService.class");

    public UserService() {
        user = new User();
        mySqlDaoFactory = new MySqlDaoFactory();
    }


    public long createUser(String nickname, String username, String password) {
        user.setNickname(nickname);
        user.setName(username);
        user.setPassword(encrypt(password));
        return persist();
    }

    public void createUser(String nickname, String username, String password, String surname) {
        createUser(nickname, username, password);
        user.setSurname(surname);
        update();
    }

    private String encrypt(String message) {
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            log.error("Can't create massage digest", e);
        }
        messageDigest.reset();
        messageDigest.update(message.getBytes());

        return byteArrayToHexString(messageDigest.digest());
    }

    private String byteArrayToHexString(byte[] b) {

        StringBuffer sb = new StringBuffer(b.length * 2);

        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private long persist() {
        try (ConnectionPoolService connectionPoolService = new ConnectionPoolService()) {
            dao = mySqlDaoFactory.getDao(connectionPoolService.getConnection(), User.class);
            user = (User) dao.create(user);
            return user.getId();
        } catch (Exception e) {
            log.error("Exception when create user: " + user.getName() + "\n with id:" + user.getId(), e);
        }
        return 0;
    }

    private void update() {
        try (ConnectionPoolService connectionPoolService = new ConnectionPoolService()) {
            dao = mySqlDaoFactory.getDao(connectionPoolService.getConnection(), user.getClass());
            dao.update(user);
        } catch (Exception e) {
            log.error("Exception when update user: " + user.getName() + "\n with id:" + user.getId(), e);
        }
    }

    public User checkUser(String nickname, String password) {
        password = encrypt(password);
        try (ConnectionPoolService connectionPoolService = new ConnectionPoolService()) {
            checker = new UserDaoSql(connectionPoolService.getConnection());
            user = checker.checkUserAuthDate(nickname, password);
        } catch (Exception e) {
            log.error("Some problem with get all users", e);
        }
        return user;
    }
}






