package com.doubleysoft.kun.context.event;

import com.doubleysoft.kun.context.ApplicationEvent;
import com.doubleysoft.kun.context.ApplicationEventListener;

/**
 * Created by anguslean
 * 18-9-23 下午4:38
 */
public interface ApplicationEventRegister {
    void registerEvent(ApplicationEvent event, ApplicationEventListener listener);
}
