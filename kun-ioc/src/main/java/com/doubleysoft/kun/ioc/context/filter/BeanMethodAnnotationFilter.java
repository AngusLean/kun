package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDifination;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cupofish@gmail.com
 * 3/31/19 15:55
 */
public class BeanMethodAnnotationFilter extends AbstractAnnotationFilter {
    public BeanMethodAnnotationFilter(List<Class<? extends Annotation>> annotations) {
        super(annotations);
    }

    @Override
    public List<BeanDifination> filterBeans(List<BeanDifination> beans) {
        return beans.stream().filter(row -> {
            Class    klass           = row.getKlass();
            Method[] declaredMethods = klass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                for (Class<? extends Annotation> annotation : annotations) {
                    if (!method.isAnnotationPresent(annotation)) {
                        return false;
                    }
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}
