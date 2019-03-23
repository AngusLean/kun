package com.doubleysoft.kun.ioc.context.event;

import com.doubleysoft.kun.ioc.context.AbstractApplicationContext;
import com.doubleysoft.kun.ioc.context.ApplicationEvent;

/**
 * Created by anguslean
 * 18-9-23 下午5:32
 */
public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(AbstractApplicationContext context) {
        super(context);
    }

    public AbstractApplicationContext getApplicationContext() {
        return (AbstractApplicationContext) super.getSource();
    }
}
