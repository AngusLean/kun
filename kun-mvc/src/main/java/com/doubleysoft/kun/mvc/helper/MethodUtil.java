package com.doubleysoft.kun.mvc.helper;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 4/23/19 20:45
 */
public class MethodUtil {

    public static List<Method> getMethodByName(Class<?> clazz, String methodName) {
        List<Method> result  = new ArrayList<>();
        Method[]     methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                result.add(method);
            }
        }
        return result;
    }

    public static Object extractParam(Parameter parameter, Object value) {
        Class<?> type = parameter.getType();
        if ("".equals(value)) {
            if (type.equals(String.class)) {
                return value;
            }
            if (type.equals(int.class) || type.equals(double.class) ||
                    type.equals(short.class) || type.equals(long.class) ||
                    type.equals(byte.class) || type.equals(float.class)) {
                return 0;
            }
            if (type.equals(boolean.class)) {
                return false;
            }
            return null;
        }
        if (type == Boolean.TYPE) {
            return Boolean.valueOf(value.toString());
        } else if (type == Integer.TYPE) {
            return Integer.valueOf(value.toString());
        } else if (type == Float.TYPE) {
            return Float.valueOf(value.toString());
        } else if (type == Double.TYPE) {
            return Double.valueOf(value.toString());
        } else if (type == Byte.TYPE) {
            return Byte.valueOf(value.toString());
        } else if (type == Short.TYPE) {
            return Short.valueOf(value.toString());
        } else if (type == Long.TYPE) {
            return Long.valueOf(value.toString());
        } else if (type == String.class) {
            return value.toString();
        }
        return value;
    }

    public static boolean isBasicType(Class<?> type) {
        if (type.equals(Boolean.class) ||
                type.equals(Integer.class) ||
                type.equals(Float.class) ||
                type.equals(Double.class) ||
                type.equals(Byte.class) ||
                type.equals(Short.class) ||
                type.equals(Long.class) ||
                type.equals(String.class) ||
                type.equals(boolean.class) ||
                type.equals(int.class) ||
                type.equals(float.class) ||
                type.equals(byte.class) ||
                type.equals(short.class) ||
                type.equals(long.class) ||
                type.equals(char.class)) {
            return true;
        }
        return false;
    }
}
