package com.doubleysoft.kun.context.event.bean;

import com.doubleysoft.kun.context.AbstractApplicationContext;
import com.doubleysoft.kun.context.event.ApplicationContextEvent;

/**
 * Created by anguslean
 * 18-9-23 下午5:37
 */
public class ContextStartedEvent extends ApplicationContextEvent {
    public ContextStartedEvent(AbstractApplicationContext context) {
        super(context);
    }
}
