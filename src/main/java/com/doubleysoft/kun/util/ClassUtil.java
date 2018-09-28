package com.doubleysoft.kun.util;

import com.doubleysoft.kun.Ioc;
import com.doubleysoft.kun.exception.StateException;
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
    public static <T> T getInstance(Class<T> klass, Set<Class> injectAnnotations, Ioc ioc) {
        Constructor<?>[] declaredConstructors = klass.getDeclaredConstructors();
        Constructor<?> injectConstruct = null;
        //first find any construct annotation with inject interface
        //todo performence change
        for (Constructor<?> constructor : declaredConstructors) {
            if (injectAnnotations.stream().anyMatch(row -> constructor.getDeclaredAnnotation(row) != null)) {
                injectConstruct = constructor;
            }
        }
        if (injectConstruct != null) {
            return getInstance(klass, injectConstruct, injectAnnotations, ioc);
        }

        //second find is it has only one constructor
        if (declaredConstructors.length == 1) {
            return getInstance(klass, declaredConstructors[0], injectAnnotations, ioc);
        }

        Optional<Constructor<?>> noParamConstruct = Arrays.stream(declaredConstructors).filter(row -> row.getParameterCount() == 0)
                .findAny();
        if (!noParamConstruct.isPresent()) {
            log.error("error in init class :{}, find many constructor but has not  annotation with :{} find", klass, injectAnnotations);
            throw new StateException("error in init bean " + klass.getName());
        }
        //last call newInstance method directly
        return ReflectionUtil.newInstance(klass);
    }

    private static <T> T getInstance(Class<T> klass, Constructor<?> constructor, Set<Class> injectAnnotations, Ioc ioc) {
        int constructParamCount = constructor.getParameterCount();
        if (constructParamCount == 0) {
            T instance = ReflectionUtil.newInstanceByConstruct(constructor);
            //for default constructor, support mark field as a inject resource
            for (Field field : klass.getDeclaredFields()) {
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
            Object param = ioc.getBean(paramType);
            if (param == null) {
                log.error("can not get instance of class :{}, construct : {} , param type: {} " +
                        "does not exist", klass, constructor, paramType);
                throw new StateException("fail in get instance");
            }
            paramObjects.add(param);
        }
        return ReflectionUtil.newInstanceByConstruct(constructor, paramObjects.toArray());
    }


}
