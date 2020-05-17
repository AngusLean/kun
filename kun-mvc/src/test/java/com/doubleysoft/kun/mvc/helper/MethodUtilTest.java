package com.doubleysoft.kun.mvc.helper;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
public class MethodUtilTest {

    @Test
    public void extractParamObject() throws NoSuchMethodException {
        final String TEST_VALUE = "Hello World";
        Method testMethod1 = getClass().getMethod("testMethod1", Object.class);
        Parameter[] parameters = testMethod1.getParameters();
        Assert.assertEquals(1, parameters.length);
        Object target = MethodUtil.extractParam(parameters[0], TEST_VALUE);
        Assert.assertEquals(target, TEST_VALUE);
    }

    @Test
    public void extractParamInt() throws NoSuchMethodException {
        final int TEST_VALUE = 123;
        Method testMethod1 = getClass().getMethod("testMethod2", int.class);
        Parameter[] parameters = testMethod1.getParameters();
        Assert.assertEquals(1, parameters.length);
        Object target = MethodUtil.extractParam(parameters[0], TEST_VALUE);
        Assert.assertEquals(target, TEST_VALUE);
    }

    @Test
    public void extractParamFloat() throws NoSuchMethodException {
        final Float TEST_VALUE = Float.valueOf(1024f);
        Method testMethod1 = getClass().getMethod("testMethod3", Float.class);
        Parameter[] parameters = testMethod1.getParameters();
        Assert.assertEquals(1, parameters.length);
        Object target = MethodUtil.extractParam(parameters[0], TEST_VALUE);
        Assert.assertEquals(target, TEST_VALUE);
    }

    @Test
    public void extractParamList() throws NoSuchMethodException {
        final String TEST_VALUE = "['name1']";
        List<String> TARGET = Arrays.asList("name1");
        Method testMethod4 = getClass().getMethod("testMethod4", List.class);
        Parameter[] parameters = testMethod4.getParameters();
        Assert.assertEquals(1, parameters.length);
        Object target = MethodUtil.extractParam(parameters[0], TEST_VALUE);
        Assert.assertEquals(target, TARGET);
    }


    public void testMethod1(Object name) {

    }

    public void testMethod2(int age) {

    }

    public void testMethod3(Float age) {

    }

    public void testMethod4(List<String> names) {

    }
}