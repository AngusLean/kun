package com.doubleysoft.kun.ioc.util;

import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.exception.StateException;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by anguslean
 * 18-9-15 下午6:09
 */
@Slf4j
public class ClassUtil {

    public static <T> T getInstance(Class<T> clazz, Set<Class> injectAnnotations, Ioc ioc) {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        Constructor<?>   injectConstruct      = null;
        //first find any construct annotation with inject interface
        //todo performence change
        if (injectAnnotations != null) {
            for (Constructor<?> constructor : declaredConstructors) {
                if (injectAnnotations.stream().anyMatch(row -> constructor.getDeclaredAnnotation(row) != null)) {
                    injectConstruct = constructor;
                }
            }
        }

        if (injectConstruct != null) {
            return getInstance(clazz, injectConstruct, injectAnnotations, ioc);
        }

        //second find is it has only one constructor
        if (declaredConstructors.length == 1) {
            return getInstance(clazz, declaredConstructors[0], injectAnnotations, ioc);
        }

        Optional<Constructor<?>> noParamConstruct = Arrays.stream(declaredConstructors)
                .filter(row -> row.getParameterCount() == 0)
                .findAny();
        if (!noParamConstruct.isPresent()) {
            log.error("error in init class :{}, find many constructor but none annotationed with :{} find",
                    clazz, injectAnnotations);
            throw new StateException("error in init bean " + clazz.getName());
        }
        //last call newInstance method directly
        return ReflectionUtil.newInstance(clazz);
    }

    private static <T> T getInstance(Class<T> clazz, Constructor<?> constructor, Set<Class> injectAnnotations, Ioc ioc) {
        int constructParamCount = constructor.getParameterCount();
        if (constructParamCount == 0) {
            T instance = ReflectionUtil.newInstanceByConstruct(constructor);
            //for default constructor, support mark field as a inject resource
            for (Field field : clazz.getDeclaredFields()) {
                List<Annotation> declaredAnnotations = Arrays.asList(field.getDeclaredAnnotations());
                if (declaredAnnotations.stream()
                        .map(annotation -> annotation.annotationType())
                        .anyMatch(injectAnnotations::contains)) {
                    ReflectionUtil.setFiledValue(field, instance, ioc.getBean(field.getType()));
                }
            }
            return instance;
        }
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        List<Object> paramObjects = new ArrayList<>(constructParamCount);
        for (Class<?> paramType : parameterTypes) {
            //firstly we try to add a new instance
            ioc.addBean(paramType);
            Object param = ioc.getBean(paramType);
            if (param == null) {
                log.error("can not get instance of class :{}, construct : {} , param type: {} " +
                        "does not exist", clazz, constructor, paramType);
                throw new StateException("fail in get instance");
            }
            paramObjects.add(param);
        }
        return ReflectionUtil.newInstanceByConstruct(constructor, paramObjects.toArray());
    }


}
