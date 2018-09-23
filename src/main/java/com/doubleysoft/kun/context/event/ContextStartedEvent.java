package com.doubleysoft.kun.context.event;

import com.doubleysoft.kun.context.ApplicationContext;

/**
 * Created by anguslean
 * 18-9-23 下午5:37
 */
public class ContextStartedEvent extends ApplicationContextEvent {
    public ContextStartedEvent(ApplicationContext context) {
        super(context);
    }
}
