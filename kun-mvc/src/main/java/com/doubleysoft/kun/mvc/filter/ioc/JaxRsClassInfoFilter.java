package com.doubleysoft.kun.mvc.filter.ioc;

import com.doubleysoft.kun.ioc.context.ResourceInfo;
import com.doubleysoft.kun.ioc.scanner.ClassInfoFilter;
import com.doubleysoft.kun.mvc.MvcContants;

/**
 * @author cupofish@gmail.com
 * javax.ws.rs-api annotation
 * 3/24/19 17:16
 */
public class JaxRsClassInfoFilter implements ClassInfoFilter {
    @Override
    public boolean filterResourceClassInfo(ResourceInfo resourceInfo) {
        return resourceInfo.isAnnotationWith(MvcContants.getDefaultReqAnnotations());
    }
}
