package com.doubleysoft.kun.ioc.scanner;


import com.doubleysoft.kun.ioc.context.ClassInfo;

/**
 * Created by anguslean
 * 18-9-9 下午7:05
 */
public class DefaultClassPathScannerImpl extends AbstractClassPathScannerImpl
        implements ClassInfoFilterTraits {

    @Override
    protected boolean classFilter(ClassInfo classInfo) {
        return this.filterResourceClassInfo(classInfo);
    }
}
