package com.doubleysoft.kun.context.event;

import com.doubleysoft.kun.context.ApplicationEvent;
import com.doubleysoft.kun.context.ApplicationEventListener;

import java.util.*;

/**
 * Created by anguslean
 * 18-9-23 下午4:32
 */
public class DefaultApplicationEventManager implements ApplicationEventDispatch,ApplicationEventRegister {
    private Map<ApplicationEvent, List<ApplicationEventListener>> handlers;

    public DefaultApplicationEventManager(){
        handlers = new HashMap<>(10, 1);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        for(ApplicationEventListener listener : handlers.getOrDefault(event, Collections.emptyList())){
            listener.listen(event);
        }
    }

    @Override
    public void registerEvent(ApplicationEvent event, ApplicationEventListener listener) {
        handlers.getOrDefault(event, new ArrayList<>()).add(listener);
    }
}
