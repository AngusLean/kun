package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;
import com.doubleysoft.kun.scanner.test1.Demo3;
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
        scanner = new DefaultClassPathScannerImpl();
    }

    @Test
    public void testScan() {
        Set<ClassInfo> results = scanner.scan("com.doubleysoft.kun.scanner.test");
        Assert.assertEquals(results.size(), 2);
    }

    @Test
    public void testScanCs() throws ClassNotFoundException {
        Set<ClassInfo> results = scanner.scan("com.doubleysoft.kun.scanner.test1");
        Assert.assertTrue(results.iterator().hasNext());
        String className = results.iterator().next().getClassName();
        Assert.assertEquals("com.doubleysoft.kun.scanner.test1.Demo3", className);
        Class klass = Class.forName(className);
        Assert.assertEquals(klass, Demo3.class);
    }


}