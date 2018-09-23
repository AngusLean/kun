package com.doubleysoft.kun.context.event.bean;

import com.doubleysoft.kun.context.ApplicationContext;

/**
 * Created by anguslean
 * 18-9-23 下午5:31
 */
public class BeanAfterConstructEvent extends AbstractBeanEvent {
    public BeanAfterConstructEvent(String beanName, ApplicationContext source) {
        super(beanName, source);
    }
}
