package com.doubleysoft.kun.ioc.contexttest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author anguslean
 * 18-9-27 下午5:34
 * @since 0.0.1
 */
@Singleton
public class ConstructUserController {
    private UserDao userDao;

    @Inject
    public ConstructUserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDO getUserInfo() {
        return userDao.getUserDo();
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
