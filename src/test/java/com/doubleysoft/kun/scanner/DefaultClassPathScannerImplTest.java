package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;
import com.doubleysoft.kun.scanner.defaultcps.DefaultCps1;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by anguslean
 * 18-9-9 下午7:16
 */
public class DefaultClassPathScannerImplTest {
    private DefaultClassPathScannerImpl defaultClassPathScanner;

    @Before
    public void setUp() throws Exception {
        defaultClassPathScanner = new DefaultClassPathScannerImpl();
    }

    /**
     * should only scan class which annotated by Resource
     */
    @Test
    public void testScanWithFilter() {
        Set<ClassInfo> classInfoSet = defaultClassPathScanner.scan("com.doubleysoft.kun.scanner.defaultcps");
        Assert.assertEquals(classInfoSet.size(), 1);
        ClassInfo classInfo = classInfoSet.iterator().next();
        Assert.assertEquals(DefaultCps1.class, classInfo.getKlass());
    }
}