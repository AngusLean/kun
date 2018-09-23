package com.doubleysoft.kun.context.event;

import com.doubleysoft.kun.context.ApplicationContext;
import com.doubleysoft.kun.context.ApplicationEvent;

/**
 * Created by anguslean
 * 18-9-23 下午5:32
 */
public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(ApplicationContext context) {
        super(context);
    }

    public ApplicationContext getApplicationContext(){
        return (ApplicationContext)super.getSource();
    }
}
