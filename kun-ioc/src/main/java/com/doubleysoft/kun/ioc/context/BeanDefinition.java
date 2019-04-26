package com.doubleysoft.kun.ioc.context;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

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

    private Set<String> dependsBeanName;

    public BeanDefinition() {
        dependsBeanName = new HashSet<>();
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
    public Set<String> getDepends() {
        return dependsBeanName;
    }

    public void addDepend(String beanName) {
        this.dependsBeanName.add(beanName);
    }

    public boolean isSingleton() {
        return true;
    }
}
