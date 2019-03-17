package com.doubleysoft.kun.ioc.util;

import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.exception.StateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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

    @Test
    public void getInstanceWithConstructParam() {
        Mockito.when(ioc.getBean(ClassUtilTestModel3Param.class)).thenReturn(new ClassUtilTestModel3Param());
        ClassUtilTestModel3 temp = ClassUtil.getInstance(ClassUtilTestModel3.class, annotations, ioc);
        Assert.assertNotNull(temp.getClassUtilTestModel3Param());
    }

    @Test(expected = StateException.class)
    public void getInstanceWithMultiConstructParam() {
        Mockito.when(ioc.getBean(ClassUtilTestModel3Param.class)).thenReturn(new ClassUtilTestModel3Param());
        ClassUtilTestModel4 temp = ClassUtil.getInstance(ClassUtilTestModel4.class, annotations, ioc);
    }

    @Test
    public void getInstanceWithMultiConstructInjectParam() {
        Mockito.when(ioc.getBean(ClassUtilTestModel3Param.class)).thenReturn(new ClassUtilTestModel3Param());
        ClassUtilTestModel5 temp = ClassUtil.getInstance(ClassUtilTestModel5.class, annotations, ioc);
        Assert.assertEquals(temp.getName(), "Hello World 5");
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

    public static class ClassUtilTestModel3Param {

    }

    @Data
    @AllArgsConstructor
    public static class ClassUtilTestModel3 {
        private final ClassUtilTestModel3Param classUtilTestModel3Param;
    }

    @Data
    public static class ClassUtilTestModel4 {
        private final ClassUtilTestModel3Param classUtilTestModel3Param;
        private final String name;

        public ClassUtilTestModel4(String name, ClassUtilTestModel3Param classUtilTestModel3Param) {
            this.classUtilTestModel3Param = classUtilTestModel3Param;
            this.name = name;
        }

        public ClassUtilTestModel4(ClassUtilTestModel3Param classUtilTestModel3Param) {
            this.classUtilTestModel3Param = classUtilTestModel3Param;
            this.name = "Hello World 4";
        }
    }

    @Data
    public static class ClassUtilTestModel5 {
        private final ClassUtilTestModel3Param classUtilTestModel3Param;
        private final String name;

        public ClassUtilTestModel5(String name, ClassUtilTestModel3Param classUtilTestModel3Param) {
            this.classUtilTestModel3Param = classUtilTestModel3Param;
            this.name = name;
        }

        @Inject
        public ClassUtilTestModel5(ClassUtilTestModel3Param classUtilTestModel3Param) {
            this.classUtilTestModel3Param = classUtilTestModel3Param;
            this.name = "Hello World 5";
        }
    }
}