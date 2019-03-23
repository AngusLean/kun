package com.doubleysoft.kun.ioc;

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

    /**
     * get a specified type of bean instance, if current instance have not created,
     * this method will create a new instance。
     *
     * @param klass the class
     * @param <T>   type
     * @return instance of type klass
     */
    <T> T getBean(Class<T> klass);
}