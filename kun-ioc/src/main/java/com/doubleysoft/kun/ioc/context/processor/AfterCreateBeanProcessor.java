package com.doubleysoft.kun.ioc.context.processor;

import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.BeanProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author cupofish@gmail.com
 * 5/19/19 15:19
 */
@Slf4j
public class AfterCreateBeanProcessor implements BeanProcessor {
    @Override
    public <T> T proccessBean(BeanDefinition<T> beanDefinition, T object) {
        Set<String> fieldDepends = beanDefinition.getFieldDepends();
        Class<T>    clazz        = beanDefinition.getClazz();
        try {
            fieldDepends.forEach(fieldName -> {
//                clazz.getf
            });
        } catch (Exception e) {
            log.error("error in set property of bean:{}", object, e);
        }
        return null;
    }
}
