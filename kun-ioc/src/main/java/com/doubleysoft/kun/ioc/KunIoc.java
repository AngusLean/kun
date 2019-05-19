package com.doubleysoft.kun.ioc;


import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.BeanDefinitionProcessor;
import com.doubleysoft.kun.ioc.context.BeanRegistry;
import com.doubleysoft.kun.ioc.context.ClassInfo;
import com.doubleysoft.kun.ioc.context.filter.BeanFilter;
import com.doubleysoft.kun.ioc.context.processor.DependencyBeanDefinitionProcessor;
import com.doubleysoft.kun.ioc.exception.BeanInstantiationException;
import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.ioc.util.BeanDefinitionPostProcessorUtil;
import com.doubleysoft.kun.ioc.util.ReflectionUtil;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
public class KunIoc implements Ioc {
    private final Map<String, BeanDefinition<?>> container;
    private final Map<String, Object>            singletonBeans;
    private       Set<BeanDefinitionProcessor>   beanDefinitionProcessors;

    private Set<String> onBuildingInstance;

    private BeanRegistry beanRegistry;

    public KunIoc(BeanRegistry beanRegistry) {
        this(KunConfig.getDefaultPoolSize(), beanRegistry);
    }

    private KunIoc(int poolSize, BeanRegistry beanRegistry) {
        this.beanRegistry = beanRegistry;

        container = new ConcurrentHashMap<>(poolSize, 1);
        singletonBeans = new HashMap<>();
        onBuildingInstance = new HashSet<>();
        beanDefinitionProcessors = new HashSet<>();

        beanDefinitionProcessors.add(new DependencyBeanDefinitionProcessor(BeanDefinitionPostProcessorUtil.DEFAULT_INJECT_CLASS));
    }

    @Override
    public <T> void addBean(Class<T> clazz) {
        addBean(ClassInfo.builder().clazz((Class<Object>) clazz).build());
    }

    @Override
    public void addBean(ClassInfo<?> classInfo) {
        this.container.computeIfAbsent(classInfo.getKlazz().getName(), key -> {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClassInfo(classInfo);
            BeanDefinitionPostProcessorUtil.processBeanDefinition(beanDefinition, beanDefinitionProcessors);
            return beanDefinition;
        });
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return getBean(clazz.getName(), null);
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
        if (name == null || !container.containsKey(name)) {
            throw new StateException("bean:【" + name + "】 not exist");
        }
        BeanDefinition<?> beanDefinition = container.get(name);
        if (beanDefinition.isSingleton()) {
            if (!singletonBeans.containsKey(name)) {
                preInstantiationBean(name, beanDefinition);
                Object bean = doCreateBean(beanDefinition.getClazz(), vars);
                singletonBeans.put(name, bean);
                afterInstantiationBean(name, bean, beanDefinition);
            }
            return (T) singletonBeans.get(name);
        } else {
            return (T) doCreateBean(beanDefinition.getClazz(), vars);
        }
    }

    private void preInstantiationBean(String name, BeanDefinition<?> beanDefinition) {
        if (onBuildingInstance.contains(name)) {
            throw new StateException("cycle depends of bean name: " + name);
        }
        onBuildingInstance.add(name);
        for (String depend : beanDefinition.getDepends()) {
            getBean(depend);
        }
        if (beanRegistry != null) {
            this.beanRegistry.beforeBeanCreate(name, beanDefinition);
        }
    }

    private void afterInstantiationBean(String name, Object bean, BeanDefinition<?> beanDefinition) {
        onBuildingInstance.remove(name);
        if (beanRegistry != null) {
            this.beanRegistry.afterBeanCreate(name, bean, beanDefinition);
        }
    }

    private <T> T doCreateBean(Class<T> clazz, Object... vars) {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        if (declaredConstructors.length == 1) {
            Constructor<?> constructor    = declaredConstructors[0];
            Object[]       depends        = new Object[constructor.getParameterCount()];
            int            index          = 0;
            Class<?>[]     parameterTypes = constructor.getParameterTypes();
            for (Class<?> dependClass : parameterTypes) {
                Object bean = getBean(dependClass);
                if (bean == null) {
                    throw new BeanInstantiationException(clazz, "class have only one constructor, param object" + dependClass + " is not exist");
                }
                depends[index++] = bean;
            }
            return ReflectionUtil.newInstanceByConstruct(constructor, depends);
        }
        return ReflectionUtil.newInstance(clazz);
    }
}
