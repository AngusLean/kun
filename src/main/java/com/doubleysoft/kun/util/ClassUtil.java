package com.doubleysoft.kun.util;

import com.doubleysoft.kun.Ioc;
import com.doubleysoft.kun.exception.StateException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Created by anguslean
 * 18-9-15 下午6:09
 */
@Slf4j
public class ClassUtil {
    public static <T> T getInstance(Class<T> klass, Set<Class> injectAnotations, Ioc ioc) {
        T instance;

        try {
            Constructor<?>[] declaredConstructors = klass.getDeclaredConstructors();
            Constructor<?> injectConstruct = null;
            //first find any constrcutor annotation with inject interface
            //todo performence change
            for (Constructor<?> constructor : declaredConstructors) {
                if (injectAnotations.stream().anyMatch(row -> constructor.getDeclaredAnnotation(row) != null)) {
                    injectConstruct = constructor;
                }
            }
            if (injectConstruct != null) {
                return getInstance(klass, injectConstruct, ioc);
            }

            //secound find if it has only one constructor
            if (declaredConstructors.length == 1) {
                return getInstance(klass, declaredConstructors[0], ioc);
            }

            Optional<Constructor<?>> noParamConstruct = Arrays.stream(declaredConstructors).filter(row -> row.getParameterCount() == 0)
                    .findAny();
            if (!noParamConstruct.isPresent()) {
                log.error("error in init class :{}, find many constructor but has not  annotation with :{} find", klass, injectAnotations);
                throw new StateException("error in init bean " + klass.getName());
            }
            //last call newInstance method directly
            instance = klass.newInstance();
        } catch (StateException e) {
            throw e;
        } catch (InstantiationException e) {
            log.error("error in init class {}", klass, e);
            throw new StateException("error in init bean " + klass.getName());
        } catch (IllegalAccessException e) {
            log.error("error in init class {}, IllegalAccess", klass);
            throw new StateException("error in init bean " + klass.getName() + ", illegal access");
        }

        return instance;
    }

    private static <T> T getInstance(Class<T> klass, Constructor<?> constructor, Ioc ioc) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        List<Object> paramterObjects = new ArrayList<>(constructor.getParameterCount());
        for (Class<?> paramterType : parameterTypes) {
            Object param = ioc.getBean(paramterType);
            if (param == null) {
                log.error("can not get instance of class :{}, construct : {} , param type: {} " +
                        "does not exist", klass, constructor, paramterType);
                throw new StateException("fail in get instance");
            }
            paramterObjects.add(param);
        }
        try {
            return (T) constructor.newInstance(paramterObjects.toArray());
        } catch (Exception e) {
            log.error("fail in call newInstance of class :{} ", klass, e);
            throw new StateException("fail in get instance");
        }
    }
}
