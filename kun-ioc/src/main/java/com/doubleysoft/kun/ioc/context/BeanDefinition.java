package com.doubleysoft.kun.ioc.context;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
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

    //field depend
    private Set<String> dependsBeanName;
    // construct depend
    private Set<String> csdependsBeanName;

    public BeanDefinition() {
        dependsBeanName = new HashSet<>();
        csdependsBeanName = new HashSet<>();
    }

    public Class<T> getClazz() {
        return classInfo.getKlazz();
    }

    public void setClazz(Class<T> clazz) {
        if (this.classInfo == null) {
            classInfo = new ClassInfo<>();
        }
        classInfo.setClazz(clazz);
    }


    /**
     * return all depends bean name
     *
     * @return
     */
    public Set<String> getDepends() {
        HashSet tmp = new HashSet();
        tmp.addAll(dependsBeanName);
        tmp.addAll(csdependsBeanName);
        return tmp;
    }

    public Set<String> getFieldDepends() {
        return Collections.unmodifiableSet(dependsBeanName);
    }

    public void addDepend(String beanName, boolean isField) {
        if (isField) {
            this.dependsBeanName.add(beanName);
        } else {
            this.csdependsBeanName.add(beanName);
        }

    }

    public boolean isSingleton() {
        return true;
    }
}
