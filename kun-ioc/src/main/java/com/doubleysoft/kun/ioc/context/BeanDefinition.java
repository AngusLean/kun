package com.doubleysoft.kun.ioc.context;


import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.KunConfig;
import com.doubleysoft.kun.ioc.util.ClassUtil;
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
    private Ioc ioc;

    public BeanDefinition(Ioc ioc) {
        this.ioc = ioc;
    }

    @Getter
    @Setter
    private ClassInfo<T> classInfo;

    private T instance;

    public <T> T getInstance() {
        if (instance == null) {
            instance = ClassUtil.getInstance(getKlass(), KunConfig.getInjectAnnotations(), ioc);
        }
        return (T) instance;
    }

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
