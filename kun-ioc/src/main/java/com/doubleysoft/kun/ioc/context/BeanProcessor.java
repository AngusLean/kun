package com.doubleysoft.kun.ioc.context;

/**
 * @author cupofish@gmail.com
 * 5/19/19 15:17
 */
public interface BeanProcessor {
    <T> T proccessBean(BeanDefinition<T> beanDefinition, T bean);
}
