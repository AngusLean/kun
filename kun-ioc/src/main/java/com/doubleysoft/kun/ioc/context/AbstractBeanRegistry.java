package com.doubleysoft.kun.ioc.context;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class AbstractBeanRegistry implements BeanRegistry {


    @Override
    public void registerBean(String beanName, Object bean) {

    }

    @Override
    public void beforeBeanCreate(String beanName, BeanDefinition<?> beanDefinition) {

    }

    @Override
    public void afterBeanCreate(String beanName, Object bean, BeanDefinition<?> beanDefinition) {

    }
}
