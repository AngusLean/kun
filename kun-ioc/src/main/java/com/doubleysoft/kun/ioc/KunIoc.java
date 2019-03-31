package com.doubleysoft.kun.ioc;


import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.context.ClassInfo;
import com.doubleysoft.kun.ioc.context.filter.BeanFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
public class KunIoc implements Ioc {
    private final Map<String, BeanDifination<?>> container;

    public KunIoc() {
        this(KunConfig.getDefaultPoolSize());
    }

    private KunIoc(int poolSize) {
        container = new ConcurrentHashMap<>(poolSize, 1);
    }

    @Override
    public <T> void addBean(Class<T> klass) {
        String simpleName = klass.getName();
        container.computeIfAbsent(simpleName, key -> {
            BeanDifination<T> beanDifination = new BeanDifination<>();
            //fuck java generic
            beanDifination.setClassInfo((ClassInfo<T>) ClassInfo.builder().klass((Class<Object>) klass).build());
            return beanDifination;
        });
    }

    @Override
    public void addBean(ClassInfo<?> classInfo) {
        this.container.computeIfAbsent(classInfo.getKlass().getName(), key -> {
            BeanDifination beanDifination = new BeanDifination();
            beanDifination.setClassInfo(classInfo);
            return beanDifination;
        });
    }

    @Override
    public <T> T getBean(Class<T> klass) {
        if (container.containsKey(klass.getName())) {
            //maybe call Ioc.getBean recycle
            return container.get(klass.getName()).getInstance(KunConfig.getInjectAnotations(), this);
        }
        return null;
    }

    @Override
    public List<BeanDifination> getBean(List<BeanFilter> beanFilters) {
        List<BeanDifination> result = new ArrayList<>(container.values());
        if (beanFilters == null || beanFilters.size() == 0) {
            return result;
        }
        for (BeanFilter filter : beanFilters) {
            result = filter.filterBeans(result);
        }
        return result;
    }

}
