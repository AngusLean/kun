package com.doubleysoft.kun.mvc.filter.ioc;

import com.doubleysoft.kun.ioc.context.ResourceInfo;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.ApplicationPath;

/**
 * @author cupofish@gmail.com
 * 3/24/19 17:32
 */
public class JaxRsResourceInfoFilterTest {

    @Test
    public void filterResourceClassInfo() {
        Assert.assertTrue(new JaxRsClassInfoFilter().filterResourceClassInfo(ResourceInfo.builder()
                .className(JaxRsClassInfoFilterTestDO.class.getName())
                .build()));
    }

    @ApplicationPath("/")
    public class JaxRsClassInfoFilterTestDO {

    }
}