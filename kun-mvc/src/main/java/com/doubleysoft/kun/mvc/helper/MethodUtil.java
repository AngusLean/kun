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

    public static List<Method> getMethodByName(Class<?> klass, String methodName) {
        List<Method> result  = new ArrayList<>();
        Method[]     methods = klass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                result.add(method);
            }
        }
        return result;
    }

    public static Object extractParam(Parameter parameter, Object value) {
        if (parameter.getType() == Boolean.TYPE) {
            return Boolean.valueOf(value.toString());
        } else if (parameter.getType() == Integer.TYPE) {
            return Integer.valueOf(value.toString());
        } else if (parameter.getType() == Float.TYPE) {
            return Float.valueOf(value.toString());
        } else if (parameter.getType() == Double.TYPE) {
            return Double.valueOf(value.toString());
        } else if (parameter.getType() == Byte.TYPE) {
            return Byte.valueOf(value.toString());
        } else if (parameter.getType() == Short.TYPE) {
            return Short.valueOf(value.toString());
        } else if (parameter.getType() == Long.TYPE) {
            return Long.valueOf(value.toString());
        } else if (parameter.getType() == String.class) {
            return value.toString();
        }
        return value;
    }
}
