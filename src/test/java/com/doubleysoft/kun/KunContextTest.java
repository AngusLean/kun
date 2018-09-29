package com.doubleysoft.kun;

import com.doubleysoft.kun.contexttest.ConstructUserController;
import com.doubleysoft.kun.contexttest.InjectUserController;
import com.doubleysoft.kun.contexttest.NormalUserController;
import com.doubleysoft.kun.contexttest.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anguslean
 * 18-9-9 下午6:50
 */
public class KunContextTest {
    private KunContext kunContext;

    @Before
    public void setUp() {
        kunContext = new KunContext(this.getClass().getPackage().getName() + ".contexttest");
        kunContext.init();
    }

    @Test
    public void init() {
        UserDao userDao = kunContext.getBean(UserDao.class);
        ConstructUserController constructUserController = kunContext.getBean(ConstructUserController.class);
        InjectUserController injectUserController = kunContext.getBean(InjectUserController.class);

        Assert.assertNotNull(constructUserController);
        Assert.assertNotNull(userDao);
        Assert.assertNotNull(injectUserController);
        Assert.assertEquals(userDao, kunContext.getBean(UserDao.class));
        Assert.assertEquals(constructUserController, kunContext.getBean(ConstructUserController.class));
        Assert.assertTrue(userDao.getUserDo().getName().equals("UserDaoTest"));
        Assert.assertTrue(constructUserController.getUserInfo().getName().equals("UserDaoTest"));
        Assert.assertTrue(injectUserController.getUserInfo().getName().equals("UserDaoTest"));
        Assert.assertEquals(injectUserController.getUserDao(), userDao);
        Assert.assertEquals(constructUserController.getUserDao(), userDao);
        Assert.assertEquals(injectUserController.getUserDao(), constructUserController.getUserDao());

        NormalUserController normalUserController = kunContext.getBean(NormalUserController.class);
        Assert.assertNull(normalUserController);
    }
}