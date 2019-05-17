package com.doubleysoft.kun.ioc.exception;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class BeanInstantiationException extends StateException {
    private Class<?> beanClass;

    public BeanInstantiationException(Class<?> klazz, String message) {
        super(message);
        this.beanClass = klazz;
    }
}
