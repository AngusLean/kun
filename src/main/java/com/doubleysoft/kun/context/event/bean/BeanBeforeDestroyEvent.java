package com.doubleysoft.kun.context.event.bean;

import com.doubleysoft.kun.context.AbstractApplicationContext;

/**
 * Created by anguslean
 * 18-9-23 下午5:32
 */
public class BeanBeforeDestroyEvent extends AbstractBeanEvent {
    public BeanBeforeDestroyEvent(String beanName, AbstractApplicationContext source) {
        super(beanName, source);
    }
}
