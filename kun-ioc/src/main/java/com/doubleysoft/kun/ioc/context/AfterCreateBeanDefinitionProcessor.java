package com.doubleysoft.kun.ioc.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class AfterCreateBeanDefinitionProcessor implements BeanDefinitionProcessor {
    private Set<Annotation> injectFieldAnnotations;

    public AfterCreateBeanDefinitionProcessor(Set<Annotation> annotations) {
        injectFieldAnnotations = annotations;
    }

    @Override
    public void proccess(BeanDefinition<?> beanDefinition) {
        Class<?> klass       = beanDefinition.getKlass();
        Field[]  klassFields = klass.getFields();
        for (Field field : klassFields) {
            if (field.getAnnotations().length != 0) {
                injectFieldAnnotations.stream()
                        .filter(row -> field.getAnnotation(row.annotationType()) != null)
                        .findAny()
                        .ifPresent(row -> beanDefinition.addDepend(field.getName()));
            }
        }

        Method[] klassMethods = klass.getMethods();
        for (Method method : klassMethods) {
            if (method.getAnnotations().length != 0) {

            }
        }

    }
}
