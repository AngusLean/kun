package com.doubleysoft.kun.ioc.scanner;

import com.doubleysoft.kun.ioc.context.ClassInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anguslean
 * 18-9-9 下午7:06
 */
public class JavaxInjectClassInfoFilterTest {
    private JavaxInjectClassInfoFilter ClassInfoFilterTraits;

    @Before
    public void setUp() {
        ClassInfoFilterTraits = new JavaxInjectClassInfoFilter() {
        };
    }

    @Test
    public void filterClassInfo() {
        ClassInfo classInfo = ClassInfo.builder()
                .className("com.doubleysoft.kun.ioc.scanner.test2.ResourcesTraitsTestBean")
                .build();
        Assert.assertTrue(ClassInfoFilterTraits.filterResourceClassInfo(classInfo));
    }

}