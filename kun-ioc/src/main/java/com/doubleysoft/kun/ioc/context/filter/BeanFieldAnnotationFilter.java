package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cupofish@gmail.com
 * 3/31/19 15:30
 */
public class BeanFieldAnnotationFilter extends AbstractAnnotationFilter {

    public BeanFieldAnnotationFilter(List<Class<? extends Annotation>> annotations) {
        super(annotations);
    }

    @Override
    public List<BeanDefinition> filterBeans(List<BeanDefinition> beans) {
        return beans.stream().filter(row -> {
            Class   klass          = row.getKlass();
            Field[] declaredFields = klass.getDeclaredFields();
            for (Field field : declaredFields) {
                for (Class<? extends Annotation> annotation : annotations) {
                    if (!field.isAnnotationPresent(annotation)) {
                        return false;
                    }
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}
