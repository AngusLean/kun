package com.doubleysoft.kun.ioc.util;

import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.BeanDefinitionProcessor;
import com.doubleysoft.kun.ioc.context.BeanProcessor;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class PostProcessorUtil {
    public static Set<Class<? extends Annotation>> DEFAULT_INJECT_CLASS;

    static {
        DEFAULT_INJECT_CLASS = new HashSet<>();
        DEFAULT_INJECT_CLASS.add(Inject.class);
    }

    public static void processBeanDefinition(BeanDefinition<?> beanDefinition, Set<BeanDefinitionProcessor> beanDefinitionProcessors) {
        for (BeanDefinitionProcessor processor : beanDefinitionProcessors) {
            processor.proccess(beanDefinition);
        }
    }

    public static <T> T processBean(BeanDefinition<?> beanDefinition, Set<BeanProcessor> beanProcessors, T bean) {
        for (BeanProcessor beanProcessor : beanProcessors) {
            bean = beanProcessor.proccessBean((BeanDefinition<T>) beanDefinition, bean);
        }
        return bean;
    }
}
