package com.doubleysoft.kun.ioc.context;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
@Slf4j
public class BeanDefinition<T> {

    @Getter
    @Setter
    private ResourceInfo<T> resourceInfo;

    private Set<Depend> depends;

    public BeanDefinition() {
        depends = new HashSet<>();
    }

    public Class<T> getClazz() {
        return resourceInfo.getKlazz();
    }

    public void setClazz(Class<T> clazz) {
        if (this.resourceInfo == null) {
            resourceInfo = new ResourceInfo<>();
        }
        resourceInfo.setClazz(clazz);
    }


    public Set<String> getDependsName() {
        return depends.stream()
                .map(row -> row.getName())
                .collect(Collectors.toSet());
    }

    public Set<Depend> getDepends() {
        return depends;
    }

    public void addDepend(Depend depend) {
        depends.add(depend);
    }

    public boolean isSingleton() {
        return true;
    }
}
