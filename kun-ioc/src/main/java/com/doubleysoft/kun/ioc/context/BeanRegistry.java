package com.doubleysoft.kun.ioc.context;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public interface BeanRegistry {
    /**
     * register a bean isntance
     *
     * @param beanName
     * @param bean
     */
    void registerBean(String beanName, Object bean);

    /**
     * before bean create callback
     *
     * @param beanName
     */
    void beforeBeanCreate(String beanName, BeanDefinition<?> beanDefinition);


    /**
     * after bean create callback
     *
     * @param beanName
     */
    void afterBeanCreate(String beanName, Object bean, BeanDefinition<?> beanDefinition);
}
