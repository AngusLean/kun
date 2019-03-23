package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.context.event.ApplicationEventDispatch;
import com.doubleysoft.kun.ioc.context.event.DefaultApplicationEventManager;

/**
 * Created by anguslean
 * 18-9-23 下午5:33
 */
public class AbstractApplicationContext {
    /**
     * ioc container
     */
    private Ioc ioc;

    /**
     * event dispatch
     */
    private ApplicationEventDispatch applicationEventDispatch;

    public AbstractApplicationContext(Ioc ioc) {
        this.ioc = ioc;
        this.applicationEventDispatch = new DefaultApplicationEventManager();
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventDispatch.publishEvent(event);
    }

    public void addBean(Class<?> klass) {
        this.ioc.addBean(klass);
    }

    public <T> T getBean(Class<T> klass) {
        return this.ioc.getBean(klass);
    }
}
