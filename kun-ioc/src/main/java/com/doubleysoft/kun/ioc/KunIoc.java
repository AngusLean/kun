package com.doubleysoft.kun.ioc;


import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.context.filter.BeanFilter;

import java.util.ArrayList;
import java.util.Collections;
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
        String simpleName = klass.getSimpleName();
        container.computeIfAbsent(simpleName, key -> {
            BeanDifination<T> beanDifination = new BeanDifination<>();
            beanDifination.setKlass(klass);
            return beanDifination;
        });
    }

    @Override
    public <T> T getBean(Class<T> klass) {
        if (container.containsKey(klass.getSimpleName())) {
            //maybe call Ioc.getBean recycle
            return container.get(klass.getSimpleName()).getInstance(KunConfig.getInjectAnotations(), this);
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

    public Map<String, BeanDifination<?>> getAllBeans() {
        return Collections.unmodifiableMap(container);
    }


}
