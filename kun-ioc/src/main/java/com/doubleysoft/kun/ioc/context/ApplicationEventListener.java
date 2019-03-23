package com.doubleysoft.kun.ioc.context;

import java.util.EventListener;

/**
 * Created by anguslean
 * 18-9-23 下午4:35
 */
public interface ApplicationEventListener<T extends ApplicationEvent> extends EventListener {
    /**
     * onEvent specified event type, this method will aotumic call
     *
     * @param event
     */
    void onEvent(T event);
}
