package com.doubleysoft.kun.ioc.context.event;

import com.doubleysoft.kun.ioc.context.ApplicationEvent;
import com.doubleysoft.kun.ioc.context.ApplicationEventListener;

import java.util.*;

/**
 * Created by anguslean
 * 18-9-23 下午4:32
 */
public class DefaultApplicationEventManager implements ApplicationEventDispatch, ApplicationEventRegister {
    private Map<Class<? extends ApplicationEvent>, List<ApplicationEventListener>> handlers;

    public DefaultApplicationEventManager() {
        handlers = new HashMap<>(10, 1);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        for (ApplicationEventListener listener : handlers.getOrDefault(event.getClass(), Collections.emptyList())) {
            listener.onEvent(event);
        }
    }

    @Override
    public void registerEvent(Class<? extends ApplicationEvent> eventType, ApplicationEventListener listener) {
        handlers.getOrDefault(eventType, new ArrayList<>()).add(listener);
    }
}
