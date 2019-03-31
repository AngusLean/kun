package com.doubleysoft.kun.ioc.context;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 14:51
 */
public interface ApplicationContext {
    /**
     * add a bean to current context
     *
     * @param klass
     */
    void addBean(Class<?> klass);

    /**
     * get bean with specified class
     *
     * @param klass
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> klass);


    /**
     * get bean which has annotation
     *
     * @param annotations
     * @param <T>
     * @return
     */
    List<BeanDifination> getBeanWithAnnotations(List<Class<? extends Annotation>> annotations);
}
