package com.doubleysoft.kun.context;

import com.doubleysoft.kun.context.event.ApplicationEventDispatch;
import com.doubleysoft.kun.context.event.DefaultApplicationEventManager;

/**
 * Created by anguslean
 * 18-9-23 下午5:33
 */
public class ApplicationContext {
    private ApplicationEventDispatch applicationEventDispatch;

    public ApplicationContext(){
        this.applicationEventDispatch = new DefaultApplicationEventManager();
    }

    public void publishEvent(ApplicationEvent event){
        applicationEventDispatch.publishEvent(event);
    }
}
