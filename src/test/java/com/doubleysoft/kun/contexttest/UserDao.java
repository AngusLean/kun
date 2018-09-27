package com.doubleysoft.kun.contexttest;

import javax.inject.Singleton;

/**
 * @author anguslean
 * 18-9-27 下午5:31
 * @since 0.0.1
 */
@Singleton
public class UserDao {

    public UserDO getUserDo() {
        return UserDO.builder()
                .name("UserDaoTest")
                .build();
    }

}
