package com.doubleysoft.kun.context.event.bean;

import com.doubleysoft.kun.context.AbstractApplicationContext;

/**
 * Created by anguslean
 * 18-9-23 下午5:36
 */
public class BeanAfterDestroyEvent extends AbstractBeanEvent {
    public BeanAfterDestroyEvent(String beanName, AbstractApplicationContext source) {
        super(beanName, source);
    }
}
