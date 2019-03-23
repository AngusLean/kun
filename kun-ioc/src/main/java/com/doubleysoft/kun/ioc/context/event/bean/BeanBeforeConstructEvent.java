package com.doubleysoft.kun.ioc.context.event.bean;


import com.doubleysoft.kun.ioc.context.AbstractApplicationContext;

/**
 * Created by anguslean
 * 18-9-23 下午5:31
 */
public class BeanBeforeConstructEvent extends AbstractBeanEvent {
    public BeanBeforeConstructEvent(String beanName, AbstractApplicationContext source) {
        super(beanName, source);
    }
}
