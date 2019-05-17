package com.doubleysoft.kun.ioc.scanner;

import com.doubleysoft.kun.ioc.context.ClassInfo;
import com.doubleysoft.kun.ioc.scanner.defaultcps.DefaultCps2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by anguslean
 * 18-9-9 下午7:16
 */
public class DefaultAbstractClassPathScannerImplTest {
    private DefaultClassPathScannerImpl defaultClassPathScanner;

    @Before
    public void setUp() {
        defaultClassPathScanner = new DefaultClassPathScannerImpl();
    }

    /**
     * should only scan class which annotated by Resource
     */
    @Test
    public void testScanWithFilter() {
        Set<ClassInfo> classInfoSet = defaultClassPathScanner.scan("com.doubleysoft.kun.ioc.scanner.defaultcps");
        Assert.assertEquals(classInfoSet.size(), 1);
        ClassInfo classInfo = classInfoSet.iterator().next();
        Assert.assertEquals(DefaultCps2.class, classInfo.getKlazz());
    }
}