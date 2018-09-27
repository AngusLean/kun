package com.doubleysoft.kun.context.event;

import com.doubleysoft.kun.context.AbstractApplicationContext;
import com.doubleysoft.kun.context.ApplicationEvent;

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
