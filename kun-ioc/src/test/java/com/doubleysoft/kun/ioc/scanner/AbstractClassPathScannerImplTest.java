package com.doubleysoft.kun.ioc.scanner;

import com.doubleysoft.kun.ioc.context.ResourceInfo;
import com.doubleysoft.kun.ioc.scanner.test1.Demo3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by anguslean
 * 18-9-9 下午3:06
 */
public class AbstractClassPathScannerImplTest {
    private Scanner scanner;

    @Before
    public void setUp() {
        scanner = new AbstractClassPathScannerImpl() {
            @Override
            public void addClassInfoFilter(ClassInfoFilter classInfoFilter) {

            }

            @Override
            public boolean filterResourceClassInfo(ResourceInfo resourceInfo) {
                return true;
            }

        };
    }

    @Test
    public void testScan() {
        Set<ResourceInfo> results = scanner.scan("com.doubleysoft.kun.ioc.scanner.test");
        Assert.assertEquals(results.size(), 2);
    }

    @Test
    public void testScanCs() throws ClassNotFoundException {
        Set<ResourceInfo> results = scanner.scan("com.doubleysoft.kun.ioc.scanner.test1");
        Assert.assertTrue(results.iterator().hasNext());
        String className = results.iterator().next().getClassName();
        Assert.assertEquals("com.doubleysoft.kun.ioc.scanner.test1.Demo3", className);
        Class clazz = Class.forName(className);
        Assert.assertEquals(clazz, Demo3.class);
    }


}