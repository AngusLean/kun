package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDifination;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 15:57
 */
public class BeanMethodAnnotationFilterTest {
    @Test
    public void filterBeans() throws ClassNotFoundException {
        BeanMethodAnnotationFilter filter          = new BeanMethodAnnotationFilter(Arrays.asList(Inject.class));
        List<BeanDifination>       beanDifinations = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            BeanDifination beanDifination = new BeanDifination();
            beanDifination.setKlass(Class.forName(this.getClass().getName() + "$BeanMethodAnnotationFilterTestClass" + i));
            beanDifinations.add(beanDifination);
        }

        List<BeanDifination> result = filter.filterBeans(beanDifinations);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getKlass(), BeanMethodAnnotationFilterTest.BeanMethodAnnotationFilterTestClass1.class);
    }

    private static class BeanMethodAnnotationFilterTestClass1 {
        @Inject
        public void setName(String name) {

        }
    }

    private static class BeanMethodAnnotationFilterTestClass2 {
        @Inject
        private Object lock;

        public void setName(String name) {

        }
    }

    @Singleton
    private static class BeanMethodAnnotationFilterTestClass3 {
        public void setName(String name) {

        }
    }

}