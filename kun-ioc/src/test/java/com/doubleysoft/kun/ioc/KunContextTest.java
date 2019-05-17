package com.doubleysoft.kun.ioc;

import com.doubleysoft.kun.ioc.contexttest.ConstructUserController;
import com.doubleysoft.kun.ioc.contexttest.InjectUserController;
import com.doubleysoft.kun.ioc.contexttest.NormalUserController;
import com.doubleysoft.kun.ioc.contexttest.UserDao;
import com.doubleysoft.kun.ioc.contexttest.filter.TestAnnotation;
import com.doubleysoft.kun.ioc.contexttest.filter.model.ContextFilterTestDO;
import com.doubleysoft.kun.ioc.scanner.ClassInfoFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

    }

    @Ignore
    public void init() {
        kunContext.init();
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

    @Test
    public void testClassInfoFilter() {
        ClassInfoFilter classInfoFilter = classInfo -> classInfo.getKlass().isAnnotationPresent(TestAnnotation.class);
        kunContext.addClassInfoFilter(classInfoFilter);
        kunContext.init();
        ContextFilterTestDO bean = kunContext.getBean(ContextFilterTestDO.class);
        Assert.assertNotNull(bean);
    }


}