package com.doubleysoft.kun.ioc;

import com.doubleysoft.kun.ioc.exception.StateException;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author anguslean
 * 18-9-6 下午10:16
 * @since 0.0.1
 */
public class KunIocTest {
    private Ioc ioc;

    @Before
    public void setUp() {
        ioc = new KunIoc();
    }

    @Test
    public void addBean() {
        ioc.addBean(TestKlass.class);
        TestKlass val = ioc.getBean(TestKlass.class);
        Assert.assertNotNull(val);

        //should not equal new() object
        TestKlass customBean = new TestKlass();
        Assert.assertNotEquals(val, customBean);

        //should keep single-instance
        TestKlass val1 = ioc.getBean(TestKlass.class);
        Assert.assertEquals(val, val1);
    }

    @Test(expected = StateException.class)
    public void addPrivateBean() {
        ioc.addBean(PrivateTestKlass.class);
        PrivateTestKlass val = ioc.getBean(PrivateTestKlass.class);
        Assert.assertNotNull(val);
    }

    @Test
    public void addDuplicateClass() {
        ioc.addBean(TestKlass.class);
        TestKlass val = ioc.getBean(TestKlass.class);
        Assert.assertNotNull(val);
        ioc.addBean(TestKlass.class);
        TestKlass val1 = ioc.getBean(TestKlass.class);
        Assert.assertNotNull(val);
        //should be one object
        Assert.assertTrue(val == val1);
        Assert.assertEquals(val, ioc.getBean(TestKlass.class));
    }

    @Getter
    @Setter
    public static class TestKlass {
        private String name;
        private String phone;
    }

    @Getter
    @Setter
    private static class PrivateTestKlass {
        private String name;
        private String phone;
    }
}