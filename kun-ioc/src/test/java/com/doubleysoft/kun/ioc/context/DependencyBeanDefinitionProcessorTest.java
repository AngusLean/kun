package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.context.processor.DependencyBeanDefinitionProcessor;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
public class DependencyBeanDefinitionProcessorTest {

    @Test
    public void testFiledInject() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClazz(AfterCreateBeanDefinitionProcessorTest1.class);
        Set<Class<? extends Annotation>> injectAnnotations = new HashSet<>();
        injectAnnotations.add(Inject.class);
        DependencyBeanDefinitionProcessor processor = new DependencyBeanDefinitionProcessor(injectAnnotations);
        processor.proccess(beanDefinition);

        Assert.assertEquals(beanDefinition.getDepends().size(), 1);
    }

    @Test
    public void testMethodInject() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClazz(AfterCreateBeanDefinitionProcessorTest3.class);
        Set<Class<? extends Annotation>> injectAnnotations = new HashSet<>();
        injectAnnotations.add(Inject.class);
        DependencyBeanDefinitionProcessor processor = new DependencyBeanDefinitionProcessor(injectAnnotations);
        processor.proccess(beanDefinition);

        Assert.assertEquals(beanDefinition.getDepends().size(), 1);
        Depend depend = (Depend) beanDefinition.getDepends().iterator().next();
        Assert.assertEquals("com.doubleysoft.kun.ioc.context.DependencyBeanDefinitionProcessorTest$AfterCreateBeanDefinitionProcessorTest2",
                depend.getName());

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