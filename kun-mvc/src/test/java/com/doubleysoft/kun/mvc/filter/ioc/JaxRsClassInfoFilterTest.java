package com.doubleysoft.kun.mvc.filter.ioc;

import com.doubleysoft.kun.ioc.context.ClassInfo;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.ApplicationPath;

/**
 * @author cupofish@gmail.com
 * 3/24/19 17:32
 */
public class JaxRsClassInfoFilterTest {

    @Test
    public void filterResourceClassInfo() {
        Assert.assertTrue(new JaxRsClassInfoFilter().filterResourceClassInfo(ClassInfo.builder()
                .className(JaxRsClassInfoFilterTestDO.class.getName())
                .build()));
    }

    @ApplicationPath("/")
    public class JaxRsClassInfoFilterTestDO {

    }
}