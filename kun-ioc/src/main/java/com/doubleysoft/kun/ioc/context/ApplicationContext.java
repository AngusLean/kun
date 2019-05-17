package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.context.filter.BeanFilter;

import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 14:51
 */
public interface ApplicationContext {
    /**
     * add a bean to current context
     *
     * @param clazz
     */
    void addBean(Class<?> clazz);

    /**
     * get bean with specified class
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> clazz);

    <T> T getBean(String name);


    /**
     * get bean which has annotation
     *
     * @param beanFilters
     * @return
     */
    List<BeanDefinition> getBeans(List<BeanFilter> beanFilters);
}
