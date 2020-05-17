package com.doubleysoft.kun.ioc.contexttest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
@Singleton
public class MethodInjectController {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
