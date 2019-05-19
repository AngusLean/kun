package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class AfterCreateBeanDefinitionProcessor implements BeanDefinitionProcessor {
    private Set<Class<? extends Annotation>> injectAnnotations;

    public AfterCreateBeanDefinitionProcessor(Set<Class<? extends Annotation>> annotations) {
        injectAnnotations = annotations;
    }

    @Override
    public void proccess(BeanDefinition<?> beanDefinition) {
        Class<?> clazz       = beanDefinition.getKlass();
        Field[]  klassFields = clazz.getDeclaredFields();
        for (Field field : klassFields) {
            if (field.getAnnotations().length != 0) {
                injectAnnotations.stream()
                        .filter(row -> field.getAnnotation(row) != null)
                        .findAny()
                        .ifPresent(row -> beanDefinition.addDepend(getBeanName(field.getType())));
            }
        }

        Method[] klassMethods = clazz.getMethods();
        for (Method method : klassMethods) {
            if (!ReflectionUtil.isObjectMethod(method) && method.getAnnotations().length != 0) {
                int index = 0;
                injectAnnotations.stream()
                        .filter(row -> method.getAnnotation(row) != null)
                        .findAny()
                        .ifPresent(row -> {
                            beanDefinition.addDepend(getBeanName(method.getParameterTypes()[0]));
                        });
            }
        }

        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (constructor.getAnnotations().length != 0) {
                injectAnnotations.stream()
                        .filter(row -> constructor.getAnnotation(row) != null)
                        .findAny()
                        .ifPresent(row -> {
                            Class<?>[] parameterTypes = constructor.getParameterTypes();
                            for (Class<?> cz : parameterTypes) {
                                beanDefinition.addDepend(getBeanName(cz));
                            }
                        });
            }
        }
    }

    private String getBeanName(Class<?> clazz) {
        return clazz.getName();
    }

}
