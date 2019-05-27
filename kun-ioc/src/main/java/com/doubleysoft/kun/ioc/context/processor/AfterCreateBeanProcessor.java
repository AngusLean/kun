package com.doubleysoft.kun.ioc.context.processor;

import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.BeanProcessor;
import com.doubleysoft.kun.ioc.context.Depend;
import com.doubleysoft.kun.ioc.util.BeanUtil;
import com.doubleysoft.kun.ioc.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author cupofish@gmail.com
 * 5/19/19 15:19
 */
@Slf4j
public class AfterCreateBeanProcessor implements BeanProcessor {
    private Ioc ioc;

    public AfterCreateBeanProcessor(Ioc ioc) {
        this.ioc = ioc;
    }

    @Override
    public <T> T proccessBean(BeanDefinition<T> beanDefinition, T bean) {
        Class<?> beanClazz = beanDefinition.getClazz();
        for (Depend depend : beanDefinition.getDepends()) {
            Field setterField = BeanUtil.getField(beanClazz, depend);
            if (setterField == null) {
                log.warn("Can not resolve depends name:{} of Object: {}", depend, bean);
            } else {
                ReflectionUtil.setFiledValue(setterField, bean, ioc.getBean(depend.getName()));
            }
        }
        return bean;
    }
}
