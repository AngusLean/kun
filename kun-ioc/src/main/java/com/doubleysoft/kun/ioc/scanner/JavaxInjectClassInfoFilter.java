package com.doubleysoft.kun.ioc.scanner;


import com.doubleysoft.kun.ioc.context.ResourceInfo;

import javax.annotation.Resource;
import javax.inject.Singleton;
import java.util.Arrays;

/**
 * Created by anguslean
 * 18-9-9 下午7:03
 */
public class JavaxInjectClassInfoFilter implements ClassInfoFilter {

    /**
     * check a class is resource for ioc container,
     * usually  means is it annotated with @Singleton
     *
     * @param resourceInfo the class info
     * @return
     */
    @Override
    public boolean filterResourceClassInfo(ResourceInfo resourceInfo) {
        return resourceInfo.isAnnotationWith(Arrays.asList(Singleton.class, Resource.class));
    }

}
