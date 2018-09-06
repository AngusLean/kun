package com.doubleysoft.kun.kun;

import com.doubleysoft.kun.kun.context.BeanDifination;

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

    public KunIoc(int poolSize) {
        container = new ConcurrentHashMap<>(poolSize, 1);
    }

    @Override
    public <T> T addBean(Class<T> klass) {
        String simpleName = klass.getSimpleName();
        container.computeIfAbsent(simpleName, key -> {
            BeanDifination<T> beanDifination = new BeanDifination<T>();
            beanDifination.setKlass(klass);
            return beanDifination;
        });
        return container.get(simpleName).getInstance();
    }

    @Override
    public <T> T getBean(Class<T> klass) {
        return container.get(klass.getSimpleName()).getInstance();
    }
}
