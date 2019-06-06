package com.doubleysoft.kun.ioc;

import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.ResourceInfo;
import com.doubleysoft.kun.ioc.context.filter.BeanFilter;

import java.util.List;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
public interface Ioc {

    /**
     * create a specified class type bean
     *
     * @param clazz the class
     * @param <T>   type
     * @return instance of type clazz
     */
    <T> void addBean(Class<T> clazz);

    /**
     * add a Bean By ResourceInfo
     *
     * @param resourceInfo
     */
    void addBean(ResourceInfo<?> resourceInfo);

    /**
     * get a specified type of bean instance, if current instance have not created,
     * this method will create a new instance。
     *
     * @param clazz the class
     * @param <T>   type
     * @return instance of type clazz
     */
    <T> T getBean(Class<T> clazz);

    <T> T getBean(String name, Object... vars);

    /**
     * get bean filter with beanFilter interface
     *
     * @param beanFilters
     * @return
     */
    List<BeanDefinition> getBeanDefinition(List<BeanFilter> beanFilters);
}
