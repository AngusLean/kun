package com.doubleysoft.kun.util;

import com.doubleysoft.kun.Ioc;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;

/**
 * Created by anguslean
 * 18-9-15 下午6:43
 */
public class ClassUtilTest {
    private Ioc ioc;
    private Set<Class> annotations;

    @Before
    public void setUp() throws Exception {
        ioc = mock(Ioc.class);
        annotations = new HashSet<>();
        annotations.add(Inject.class);
    }

    @Test
    public void getInstance() {
        ClassUtilTestModel1 temp = ClassUtil.getInstance(ClassUtilTestModel1.class, annotations, ioc);
        Assert.assertNotNull(temp);
        Assert.assertEquals(temp.getName(), "Hello World");
    }

    @Test
    public void getInstanceInjectNoParams() {
        ClassUtilTestModel2 temp = ClassUtil.getInstance(ClassUtilTestModel2.class, annotations, ioc);
        Assert.assertNotNull(temp);
        Assert.assertEquals(temp.getName(), "Hello World 2");
    }


    public static class ClassUtilTestModel1 {
        @Getter
        private String name;

        public ClassUtilTestModel1() {
            name = "Hello World";
        }
    }

    public static class ClassUtilTestModel2 {
        @Getter
        private String name;

        @Inject
        public ClassUtilTestModel2() {
            name = "Hello World 2";
        }

        public ClassUtilTestModel2(String name) {
            this.name = name;
        }
    }
}