package com.doubleysoft.kun.ioc.context;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
@Slf4j
public class BeanDefinition<T> {

    @Getter
    @Setter
    private ClassInfo<T> classInfo;

    public Class<T> getKlass() {
        return classInfo.getKlass();
    }

    public void setKlass(Class<T> klass) {
        if (this.classInfo == null) {
            classInfo = new ClassInfo<>();
        }
        classInfo.setKlass(klass);
    }

    /**
     * return all depends bean name
     *
     * @return
     */
    public List<String> getDepends() {
        return Collections.EMPTY_LIST;
    }

    public boolean isSingleton() {
        return true;
    }
}
