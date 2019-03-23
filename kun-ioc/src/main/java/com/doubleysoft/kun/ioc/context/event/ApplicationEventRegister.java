package com.doubleysoft.kun.ioc.context.event;

import com.doubleysoft.kun.ioc.context.ApplicationEvent;
import com.doubleysoft.kun.ioc.context.ApplicationEventListener;

/**
 * Created by anguslean
 * 18-9-23 下午4:38
 */
public interface ApplicationEventRegister {
    void registerEvent(Class<? extends ApplicationEvent> eventType, ApplicationEventListener listener);
}
