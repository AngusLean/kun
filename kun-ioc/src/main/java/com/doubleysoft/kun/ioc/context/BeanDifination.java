package com.doubleysoft.kun.ioc.context;


import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.util.ClassUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
@Slf4j
public class BeanDifination<T> {
    @Setter
    @Getter
    private Class<T> klass;

    private T instance;

    public <T> T getInstance(Set<Class> annotations, Ioc ioc) {
        if (instance == null) {
            instance = ClassUtil.getInstance(klass, annotations, ioc);
        }
        return (T) instance;
    }
}
