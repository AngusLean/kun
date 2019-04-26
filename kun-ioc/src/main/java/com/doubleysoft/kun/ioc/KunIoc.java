package com.doubleysoft.kun.ioc;


import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.BeanRegistry;
import com.doubleysoft.kun.ioc.context.ClassInfo;
import com.doubleysoft.kun.ioc.context.filter.BeanFilter;
import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.ioc.util.ReflectionUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
public class KunIoc implements Ioc {
    private final Map<String, BeanDefinition<?>> container;
    private final Map<String, Object> singletonBeans;

    private Set<String> onBuildingInstance;

    private BeanRegistry beanRegistry;

    public KunIoc(BeanRegistry beanRegistry) {
        this(KunConfig.getDefaultPoolSize(), beanRegistry);

    }

    private KunIoc(int poolSize, BeanRegistry beanRegistry) {
        container = new ConcurrentHashMap<>(poolSize, 1);
        singletonBeans = new HashMap<>();
        onBuildingInstance = new HashSet<>();
        this.beanRegistry = beanRegistry;
    }

    @Override
    public <T> void addBean(Class<T> klass) {
        String simpleName = klass.getName();
        container.computeIfAbsent(simpleName, key -> {
            BeanDefinition<T> beanDefinition = new BeanDefinition<>();
            //fuck java generic
            beanDefinition.setClassInfo((ClassInfo<T>) ClassInfo.builder().klass((Class<Object>) klass).build());
            return beanDefinition;
        });
    }

    @Override
    public void addBean(ClassInfo<?> classInfo) {
        this.container.computeIfAbsent(classInfo.getKlass().getName(), key -> {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClassInfo(classInfo);
            return beanDefinition;
        });
    }

    @Override
    public <T> T getBean(Class<T> klass) {
        return getBean(klass.getName(), null);
    }

    @Override
    public List<BeanDefinition> getBeanDefinition(List<BeanFilter> beanFilters) {
        List<BeanDefinition> result = new ArrayList<>(container.values());
        if (beanFilters == null || beanFilters.size() == 0) {
            return result;
        }
        for (BeanFilter filter : beanFilters) {
            result = filter.filterBeans(result);
        }
        return result;
    }

    @Override
    public <T> T getBean(String name, Object... vars) {
        if (!container.containsKey(name)) {
            throw new StateException("bean not exist");
        }
        BeanDefinition<?> beanDefinition = container.get(name);
        if (beanDefinition.isSingleton()) {
            if (!singletonBeans.containsKey(name)) {
                preInstantiationBean(name, beanDefinition);
                singletonBeans.put(name, doCreateBean(beanDefinition.getKlass(), vars));
            }
            return (T) singletonBeans.get(name);
        } else {
            return (T) doCreateBean(beanDefinition.getKlass(), vars);
        }
    }

    private void preInstantiationBean(String name, BeanDefinition<?> beanDefinition) {
        if (onBuildingInstance.contains(name)) {
            throw new StateException("circle depends of bean name: " + name);
        }
        onBuildingInstance.add(name);
        for (String depend : beanDefinition.getDepends()) {
            getBean(depend);
        }
        if (beanDefinition != null) {
            this.beanRegistry.beforeBeanCreate(name, beanDefinition);
        }
    }

    private void afterInstantiationBean(String name, Object bean, BeanDefinition<?> beanDefinition) {
        onBuildingInstance.remove(name);

        if (beanDefinition != null) {
            this.beanRegistry.afterBeanCreate(name, bean, beanDefinition);
        }

    }


    private <T> T doCreateBean(Class<T> klass, Object... vars) {
        return ReflectionUtil.newInstance(klass);
    }
}
