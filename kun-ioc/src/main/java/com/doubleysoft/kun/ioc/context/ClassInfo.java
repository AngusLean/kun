package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.exception.StateException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by anguslean
 * 18-9-9 下午5:29
 */
@Data
@Builder
@Slf4j
public class ClassInfo<T> {
    private String className;

    private Class<T> classCache;

    public Class<T> getKlass() {
        if (classCache != null) {
            return classCache;
        }
        try {
            classCache = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("fail in find class of: {}", className, e);
            throw new StateException("fail in find class");
        }
        return classCache;
    }

    public boolean isLazyInit() {
        return false;
    }

    public boolean isAnnotationWith(Class... annotationClass) {
        ensureLoadClass();
        for (Class klass : annotationClass) {
            if (classCache.isAnnotationPresent(klass)) {
                return true;
            }
        }
        return false;
    }


    private void ensureLoadClass() {
        if (classCache == null) {
            getKlass();
        }
    }
}
