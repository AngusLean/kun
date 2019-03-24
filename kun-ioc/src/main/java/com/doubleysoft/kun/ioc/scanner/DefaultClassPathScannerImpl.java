package com.doubleysoft.kun.ioc.scanner;


import com.doubleysoft.kun.ioc.context.ClassInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 18-9-9 下午7:05
 * @author anguslean
 */
public class DefaultClassPathScannerImpl extends AbstractClassPathScannerImpl {
    private List<ClassInfoFilter> classInfoFilters;

    public DefaultClassPathScannerImpl() {
        classInfoFilters = new ArrayList<>();
        classInfoFilters.add(new JavaxInjectClassInfoFilter());
    }

    @Override
    public boolean filterResourceClassInfo(ClassInfo classInfo) {
        return classInfoFilters.stream().anyMatch(row -> row.filterResourceClassInfo(classInfo));
    }

    @Override
    public void addClassInfoFilter(ClassInfoFilter classInfoFilter) {
        this.classInfoFilters.add(classInfoFilter);
    }
}
