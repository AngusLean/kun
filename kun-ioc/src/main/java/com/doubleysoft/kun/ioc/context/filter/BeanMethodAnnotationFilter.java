package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cupofish@gmail.com
 * 3/31/19 15:55
 */
public class BeanMethodAnnotationFilter extends AbstractAnnotationFilter {
    /**
     * need at last match one annotation
     */
    private boolean matchOne;

    public BeanMethodAnnotationFilter(List<Class<? extends Annotation>> annotations, boolean matchOne) {
        super(annotations);
        this.matchOne = matchOne;
    }

    @Override
    public List<BeanDefinition> filterBeans(List<BeanDefinition> beans) {
        return beans.stream().filter(row -> {
            Class    clazz           = row.getClazz();
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                for (Class<? extends Annotation> annotation : annotations) {
                    if (matchOne && method.isAnnotationPresent(annotation)) {
                        return true;
                    } else if (!matchOne && !method.isAnnotationPresent(annotation)) {
                        return false;
                    }
                }
            }
            if (matchOne) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
    }
}
