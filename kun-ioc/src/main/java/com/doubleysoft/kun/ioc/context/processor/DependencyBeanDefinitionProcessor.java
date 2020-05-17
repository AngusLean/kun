package com.doubleysoft.kun.ioc.context.processor;

import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.BeanDefinitionProcessor;
import com.doubleysoft.kun.ioc.context.Depend;
import com.doubleysoft.kun.ioc.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
public class DependencyBeanDefinitionProcessor implements BeanDefinitionProcessor {
    private Set<Class<? extends Annotation>> injectAnnotations;

    public DependencyBeanDefinitionProcessor(Set<Class<? extends Annotation>> annotations) {
        injectAnnotations = annotations;
    }

    @Override
    public void proccess(BeanDefinition<?> beanDefinition) {
        Class<?> clazz       = beanDefinition.getClazz();
        Field[]  klassFields = clazz.getDeclaredFields();
        for (Field field : klassFields) {
            if (field.getAnnotations().length != 0) {
                injectAnnotations.stream()
                        .filter(row -> field.getAnnotation(row) != null)
                        .findAny()
                        .ifPresent(row -> {
                            beanDefinition.addDepend(Depend.builder()
                                    .name(getBeanName(field.getType()))
                                    .simpleName(field.getName())
                                    .build());
                        });
            }
        }

        Method[] klassMethods = clazz.getMethods();
        for (Method method : klassMethods) {
            if (!ReflectionUtil.isObjectMethod(method) && method.getAnnotations().length != 0) {
                injectAnnotations.stream()
                        .filter(row -> method.getAnnotation(row) != null)
                        .findAny()
                        .ifPresent(row -> {
                            beanDefinition.addDepend(Depend.builder()
                                    .name(getBeanName(method.getParameterTypes()[0]))
                                    .simpleName(method.getParameters()[0].getName())
                                    .build());
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
                                beanDefinition.addDepend(Depend.builder()
                                        .name(getBeanName(cz))
                                        .simpleName(cz.getSimpleName())
                                        .isField(false)
                                        .build());
                            }
                        });
            }
        }
    }

    private String getBeanName(Class<?> clazz) {
        return clazz.getName();
    }

}
