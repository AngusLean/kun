package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.ioc.util.AsmUtil;

import java.lang.annotation.Annotation;
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
                        .ifPresent(row -> beanDefinition.addDepend(getBeanName(field.getClass())));
            }
        }

        Method[] klassMethods = clazz.getMethods();
        for (Method method : klassMethods) {
            if (method.getAnnotations().length != 0) {
                injectAnnotations.stream()
                        .filter(row -> method.getAnnotation(row) != null)
                        .findAny()
                        .ifPresent(row -> {
                            String[] methodParamNames = AsmUtil.getMethodParamNames(method);
                            if (methodParamNames.length == 0) {
                                throw new StateException("wrong inject method param count" + method.getName());
                            }
                            beanDefinition.addDepend(getBeanName(method.getParameterTypes()[0]));
                        });
            }
        }
    }

    private String getBeanName(Class<?> clazz) {
        return clazz.getName();
    }

}
