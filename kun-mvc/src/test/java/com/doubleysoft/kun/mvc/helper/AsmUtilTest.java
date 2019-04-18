package com.doubleysoft.kun.mvc.helper;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author cupofish@gmail.com
 * 4/18/19 22:57
 */
public class AsmUtilTest {


    @Test
    public void getMethodParamNames() throws NoSuchMethodException {
        Method[] methods = AsmUtilTestInner.class.getMethods();
        for (Method method : methods) {
            if (method.getName().contains("method")) {
                System.out.println(AsmUtil.getMethodParamNames(method));
            }
        }

    }

    public class AsmUtilTestInner {
        public void method1(String name, int age) {
        }

        public void method2(String name, int age, String name1) {
        }
    }
}