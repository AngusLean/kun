package com.doubleysoft.kun.ioc.exception;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
public class BeanInstantiationException extends StateException {
    private Class<?> beanClass;

    public BeanInstantiationException(Class<?> klazz, String message) {
        super(message);
        this.beanClass = klazz;
    }
}
