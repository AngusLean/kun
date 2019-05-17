package com.doubleysoft.kun.ioc.context;

import org.junit.Assert;
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
    public void testFiledInject() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setKlass(AfterCreateBeanDefinitionProcessorTest1.class);
        Set<Class<? extends Annotation>> injectAnnotations = new HashSet<>();
        injectAnnotations.add(Inject.class);
        AfterCreateBeanDefinitionProcessor processor = new AfterCreateBeanDefinitionProcessor(injectAnnotations);
        processor.proccess(beanDefinition);

        Assert.assertEquals(beanDefinition.getDepends().size(), 1);
    }

    @Test
    public void testMethodInject() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setKlass(AfterCreateBeanDefinitionProcessorTest3.class);
        Set<Class<? extends Annotation>> injectAnnotations = new HashSet<>();
        injectAnnotations.add(Inject.class);
        AfterCreateBeanDefinitionProcessor processor = new AfterCreateBeanDefinitionProcessor(injectAnnotations);
        processor.proccess(beanDefinition);

        Assert.assertEquals(beanDefinition.getDepends().size(), 1);
        Assert.assertEquals(beanDefinition.getDepends().iterator().next(), "test2");

    }

    public class AfterCreateBeanDefinitionProcessorTest1 {
        @Inject
        private AfterCreateBeanDefinitionProcessorTest2 test2;
    }

    public class AfterCreateBeanDefinitionProcessorTest3 {
        @Inject
        public void setTest2(AfterCreateBeanDefinitionProcessorTest2 test2) {

        }

        public void setTest2() {

        }
    }

    @Singleton
    public class AfterCreateBeanDefinitionProcessorTest2 {
    }
}