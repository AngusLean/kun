package com.doubleysoft.kun.ioc.scanner;


import com.doubleysoft.kun.ioc.context.ClassInfo;

import javax.annotation.Resource;
import javax.inject.Singleton;

/**
 * Created by anguslean
 * 18-9-9 下午7:03
 */
public class JavaxInjectClassInfoFilter implements ClassInfoFilter {

    /**
     * check a class is resource for ioc container,
     * usually  means is it annotated with @Singleton
     *
     * @param classInfo the class info
     * @return
     */
    @Override
    public boolean filterResourceClassInfo(ClassInfo classInfo) {
        return classInfo.getKlass().isAnnotationPresent(Singleton.class) ||
                classInfo.getKlass().isAnnotationPresent(Resource.class);
    }

    /**
     * check a class is injected resource
     * usually  means is it annotated with @Resource or @Inject
     *
     * @param classInfo the class info
     * @return
     */
   /* default boolean filterInjectedClassInfo(ClassInfo classInfo) {
        return classInfo.getKlass().isAnnotationPresent(Resource.class) ||
                classInfo.getKlass().isAnnotationPresent(Inject.class);
    }*/

}
