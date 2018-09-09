package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;

/**
 * Created by anguslean
 * 18-9-9 下午7:05
 */
public class DefaultClassPathScannerImpl extends ClassPathScannerImpl
        implements ResourcesTraits {

    @Override
    protected boolean classFilter(ClassInfo classInfo) {
        return this.filterClassInfoTrait(classInfo);
    }
}
