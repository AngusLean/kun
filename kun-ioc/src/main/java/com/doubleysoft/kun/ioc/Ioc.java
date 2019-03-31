package com.doubleysoft.kun.ioc;

import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.context.ClassInfo;
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
     * @param klass the class
     * @param <T>   type
     * @return instance of type klass
     */
    <T> void addBean(Class<T> klass);

    void addBean(ClassInfo<?> classInfo);

    /**
     * get a specified type of bean instance, if current instance have not created,
     * this method will create a new instance。
     *
     * @param klass the class
     * @param <T>   type
     * @return instance of type klass
     */
    <T> T getBean(Class<T> klass);

    /**
     * get bean filter with beanFilter interface
     *
     * @param beanFilters
     * @return
     */
    List<BeanDifination> getBean(List<BeanFilter> beanFilters);
}
