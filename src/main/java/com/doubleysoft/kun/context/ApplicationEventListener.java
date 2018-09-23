package com.doubleysoft.kun.context;

import java.util.EventListener;

/**
 * Created by anguslean
 * 18-9-23 下午4:35
 */
public interface ApplicationEventListener<T extends ApplicationEvent> extends EventListener {
    void listen(T event);
}
