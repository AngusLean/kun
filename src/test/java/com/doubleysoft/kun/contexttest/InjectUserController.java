package com.doubleysoft.kun.contexttest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author anguslean
 * 18-9-27 下午5:56
 * @since 0.0.1
 */
@Singleton
public class InjectUserController {
    @Inject
    private UserDao userDao;

    public UserDO getUserInfo() {
        return userDao.getUserDo();
    }
}
