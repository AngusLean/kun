package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.context.event.ApplicationEventDispatch;
import com.doubleysoft.kun.ioc.context.event.ApplicationEventRegister;
import com.doubleysoft.kun.ioc.context.event.DefaultApplicationEventManager;
import com.doubleysoft.kun.ioc.context.filter.BeanFilter;

import java.util.List;

/**
 * Created by anguslean
 * 18-9-23 下午5:33
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    /**
     * ioc container
     */
    protected Ioc ioc;

    /**
     * event dispatch
     */
    private ApplicationEventDispatch applicationEventDispatch;
    private ApplicationEventRegister applicationEventRegister;

    public AbstractApplicationContext(Ioc ioc) {
        this.ioc = ioc;
        DefaultApplicationEventManager eventManager = new DefaultApplicationEventManager();
        this.applicationEventDispatch = eventManager;
        this.applicationEventRegister = eventManager;
    }

    @Override
    public void addBean(Class<?> klass) {
        this.ioc.addBean(klass);
    }

    @Override
    public <T> T getBean(Class<T> klass) {
        return this.ioc.getBean(klass);
    }

    @Override
    public List<BeanDefinition> getBeans(List<BeanFilter> beanFilters) {
        return ioc.getBeanDefinition(beanFilters);
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventDispatch.publishEvent(event);
    }

    public void addEventListener(Class<? extends ApplicationEvent> eventType, ApplicationEventListener listener) {
        applicationEventRegister.registerEvent(eventType, listener);
    }
}
