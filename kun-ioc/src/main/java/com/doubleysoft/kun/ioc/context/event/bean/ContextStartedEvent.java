package com.doubleysoft.kun.ioc.context.event.bean;


import com.doubleysoft.kun.ioc.context.AbstractApplicationContext;
import com.doubleysoft.kun.ioc.context.event.ApplicationContextEvent;

/**
 * Created by anguslean
 * 18-9-23 下午5:37
 */
public class ContextStartedEvent extends ApplicationContextEvent {
    public ContextStartedEvent(AbstractApplicationContext context) {
        super(context);
    }
}
