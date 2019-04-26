package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.KunIoc;
import com.doubleysoft.kun.ioc.context.event.ApplicationEventDispatch;
import com.doubleysoft.kun.ioc.context.event.ApplicationEventRegister;
import com.doubleysoft.kun.ioc.context.event.DefaultApplicationEventManager;
import com.doubleysoft.kun.ioc.context.event.bean.BeanAfterCreateEvent;
import com.doubleysoft.kun.ioc.context.event.bean.BeanBeforeCreateEvent;
import com.doubleysoft.kun.ioc.context.filter.BeanFilter;

import java.util.List;

/**
 * Created by anguslean
 * 18-9-23 下午5:33
 */
public abstract class AbstractApplicationContext implements ApplicationContext, BeanRegistry {
    /**
     * ioc container
     */
    protected Ioc ioc;

    /**
     * event dispatch
     */
    private ApplicationEventDispatch applicationEventDispatch;
    private ApplicationEventRegister applicationEventRegister;

    public AbstractApplicationContext() {
        this.ioc = new KunIoc(null);
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
    public <T> T getBean(String name) {
        return this.ioc.getBean(name);
    }

    @Override
    public List<BeanDefinition> getBeans(List<BeanFilter> beanFilters) {
        return ioc.getBeanDefinition(beanFilters);
    }

    @Override
    public void registerBean(String beanName, Object bean) {

    }

    @Override
    public void beforeBeanCreate(String beanName, BeanDefinition<?> beanDefinition) {
        this.publishEvent(new BeanBeforeCreateEvent(beanName, this));
    }

    @Override
    public void afterBeanCreate(String beanName, Object bean, BeanDefinition<?> beanDefinition) {
        this.publishEvent(new BeanAfterCreateEvent(beanName, this));
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventDispatch.publishEvent(event);
    }

    public void addEventListener(Class<? extends ApplicationEvent> eventType, ApplicationEventListener listener) {
        applicationEventRegister.registerEvent(eventType, listener);
    }
}
