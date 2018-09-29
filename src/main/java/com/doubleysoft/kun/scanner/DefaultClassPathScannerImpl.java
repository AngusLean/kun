package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;

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
