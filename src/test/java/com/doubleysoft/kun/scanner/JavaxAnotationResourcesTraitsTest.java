package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anguslean
 * 18-9-9 下午7:06
 */
public class JavaxAnotationResourcesTraitsTest {
    private JavaxAnotationResourcesTraits JavaxAnotationResourcesTraits;

    @Before
    public void setUp() throws Exception {
        JavaxAnotationResourcesTraits = new JavaxAnotationResourcesTraits() {
        };
    }

    @Test
    public void filterClassInfo() {
        ClassInfo classInfo = ClassInfo.builder()
                .className("com.doubleysoft.kun.scanner.test2.ResourcesTraitsTestBean")
                .build();
        Assert.assertFalse(JavaxAnotationResourcesTraits.filterClassInfoTrait(classInfo));
    }

}