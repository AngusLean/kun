package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDefinition;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 15:35
 */
public class BeanFieldAnnotationFilterTest {
    @Test
    public void filterBeans() throws ClassNotFoundException {
        BeanFieldAnnotationFilter filter          = new BeanFieldAnnotationFilter(Arrays.asList(Inject.class));
        List<BeanDefinition>      beanDefinitions = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClazz(Class.forName(this.getClass().getName() + "$BeanFieldAnnotationFilterTestClass" + i));
            beanDefinitions.add(beanDefinition);
        }

        List<BeanDefinition> result = filter.filterBeans(beanDefinitions);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getClazz(), BeanFieldAnnotationFilterTestClass1.class);
    }

    private static class BeanFieldAnnotationFilterTestClass1 {
        @Inject
        private Object lock;
    }

    private static class BeanFieldAnnotationFilterTestClass2 {
        private Object lock;
    }

    @Singleton
    private static class BeanFieldAnnotationFilterTestClass3 {
        private Object lock;
    }
}