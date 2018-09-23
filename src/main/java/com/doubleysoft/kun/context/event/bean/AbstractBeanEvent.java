package com.doubleysoft.kun.context.event.bean;

import com.doubleysoft.kun.context.ApplicationContext;
import com.doubleysoft.kun.context.ApplicationEvent;
import com.doubleysoft.kun.context.event.ApplicationContextEvent;

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

    public AbstractBeanEvent(String beanName, ApplicationContext source) {
        super(source);
        this.beanName = beanName;
    }
}
