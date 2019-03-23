package com.doubleysoft.kun.ioc.context.event;


import com.doubleysoft.kun.ioc.context.ApplicationEvent;

/**
 * Created by anguslean
 * 18-9-23 下午4:29
 */
public interface ApplicationEventDispatch {
    /**
     * publish event and notify all "matching" event lisener
     *
     * @param applicationEvent
     */
    void publishEvent(ApplicationEvent applicationEvent);
}
