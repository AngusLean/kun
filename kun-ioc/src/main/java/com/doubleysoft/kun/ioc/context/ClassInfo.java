package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.exception.StateException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by anguslean
 * 18-9-9 下午5:29
 * Used in scan class from class path, and in Ioc interface generate a BeanDefinition instance
 */
@Data
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo<T> {
    private String className;

    private Class<T> klass;


    public Class<T> getKlass() {
        if (klass != null) {
            return klass;
        }
        try {
            klass = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("fail in find class of: {}", className, e);
            throw new StateException("fail in find class");
        }
        return klass;
    }

    public boolean isAnnotationWith(List<Class<? extends Annotation>> annotationClass) {
        ensureLoadClass();
        for (Class klass : annotationClass) {
            if (this.klass.isAnnotationPresent(klass)) {
                return true;
            }
        }
        return false;
    }


    private void ensureLoadClass() {
        if (klass == null) {
            getKlass();
        }
    }
}
