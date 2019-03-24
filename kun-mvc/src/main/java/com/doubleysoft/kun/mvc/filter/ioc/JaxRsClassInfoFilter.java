package com.doubleysoft.kun.mvc.filter.ioc;

import com.doubleysoft.kun.ioc.context.ClassInfo;
import com.doubleysoft.kun.ioc.scanner.ClassInfoFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

/**
 * @author cupofish@gmail.com
 * javax.ws.rs-api annotation
 * 3/24/19 17:16
 */
public class JaxRsClassInfoFilter implements ClassInfoFilter {
    @Override
    public boolean filterResourceClassInfo(ClassInfo classInfo) {
        return classInfo.isAnnotationWith(Path.class, ApplicationPath.class);
    }
}
