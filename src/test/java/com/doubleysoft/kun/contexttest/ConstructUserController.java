package com.doubleysoft.kun.contexttest;

import lombok.AllArgsConstructor;

import javax.inject.Singleton;

/**
 * @author anguslean
 * 18-9-27 下午5:34
 * @since 0.0.1
 */
@Singleton
@AllArgsConstructor
public class ConstructUserController {
    private UserDao userDao;

    public UserDO getUserInfo() {
        return userDao.getUserDo();
    }
}
