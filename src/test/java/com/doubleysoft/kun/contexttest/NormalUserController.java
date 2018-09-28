package com.doubleysoft.kun.contexttest;

/**
 * @author anguslean
 * 18-9-28 下午6:38
 * @since 0.0.1
 */
public class NormalUserController {
    private UserDao userDao;

    public UserDO getUserInfo() {
        return userDao.getUserDo();
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
