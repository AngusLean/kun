package com.doubleysoft.kun.mvc.helper;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 4/18/19 22:57
 */
public class AsmUtilTest {


    @Test
    public void testVarArguments() {
        List<Method> method3 = MethodUtil.getMethodByName(AsmUtilTestInner.class, "method3");
        Assert.assertEquals(method3.size(), 1);
        Parameter[] parameters = method3.get(0).getParameters();
        Assert.assertNotNull(parameters);
    }

    @Test
    public void testPrimitiveArguments() {
        List<Method> method4 = MethodUtil.getMethodByName(AsmUtilTestInner.class, "method4");
        Assert.assertEquals(method4.size(), 1);
        Parameter parameter = method4.get(0).getParameters()[0];
        Assert.assertTrue(parameter.getType() == Integer.TYPE);
    }

    public class AsmUtilTestInner {
        public void method1(String name, int age) {
        }

        public void method2(String name, int age, String name1) {
        }

        public void method3(String... names) {
        }

        public void method4(int arg) {
        }
    }
}