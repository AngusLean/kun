package com.doubleysoft.kun.ioc.context;

import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class AfterCreateBeanDefinitionProcessorTest {

    @Test
    public void proccess() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setKlass(AfterCreateBeanDefinitionProcessorTest1.class);
        Set<Class<? extends Annotation>> injectAnnotations = new HashSet<>();
//        injectAnnotations.add(Inject.class);
//        AfterCreateBeanDefinitionProcessor processor = new AfterCreateBeanDefinitionProcessor();
//        processor.proccess(beanDefinition);
    }

    public class AfterCreateBeanDefinitionProcessorTest1 {
        @Inject
        private AfterCreateBeanDefinitionProcessorTest2 test2;
    }

    @Singleton
    public class AfterCreateBeanDefinitionProcessorTest2 {
    }
}