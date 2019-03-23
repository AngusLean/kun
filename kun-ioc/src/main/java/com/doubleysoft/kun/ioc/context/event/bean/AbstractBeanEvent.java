package com.doubleysoft.kun.ioc.context.event.bean;

import com.doubleysoft.kun.ioc.context.AbstractApplicationContext;
import com.doubleysoft.kun.ioc.context.event.ApplicationContextEvent;

/**
 * Created by anguslean
 * 18-9-23 下午5:26
 * event assiociate with bean
 */
public class AbstractBeanEvent extends ApplicationContextEvent {
    /**
     * bean name
     */
    private String beanName;

    public AbstractBeanEvent(String beanName, AbstractApplicationContext source) {
        super(source);
        this.beanName = beanName;
    }
}
