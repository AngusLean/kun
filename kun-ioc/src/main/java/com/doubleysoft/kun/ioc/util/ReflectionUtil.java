package com.doubleysoft.kun.ioc.util;

import com.doubleysoft.kun.ioc.exception.BeanInstantiationException;
import com.doubleysoft.kun.ioc.exception.ReflectionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author anguslean
 * 18-9-28 下午6:04
 * @since 0.0.1
 */
public class ReflectionUtil {
    public static <T> T newInstance(Class<?> clazz) {
        if (clazz.isInterface()) {
            throw new BeanInstantiationException(clazz, "Specified class is an interface");
        }

        try {
            makeAccessible(clazz.getDeclaredConstructor());
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new ReflectionException(e, "error in init bean " + clazz.getName() + ", illegal access");
        }
    }

    public static <T> T newInstanceByConstruct(Constructor<?> constructor, Object... params) {
        try {
            return (T) constructor.newInstance(params);
        } catch (Exception e) {
            throw new ReflectionException(e, "fail in get instance");
        }
    }

    public static void setFiledValue(Field field, Object object, Object value) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new ReflectionException(e, "fail in set field" + field + " of object: " + object + " value: " + value);
        }
    }

    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }

    public static boolean isObjectMethod(Method method) {
        return method.getDeclaringClass() == Object.class;
    }

}
