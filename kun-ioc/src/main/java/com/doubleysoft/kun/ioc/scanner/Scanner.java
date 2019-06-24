package com.doubleysoft.kun.ioc.scanner;

import com.doubleysoft.kun.ioc.context.ResourceInfo;

import java.util.Set;

/**
 * Created by anguslean on 18-9-9.
 * @author anguslean
 */
public interface Scanner {
    /**
     * scan the package's all class, and filter with {@link ClassInfoFilter}
     *
     * @param packages
     * @return
     */
    Set<ResourceInfo> scan(String packages);

    /**
     * load class
     *
     * @param classPackage complete class path
     * @return
     */
    ResourceInfo loadClass(String classPackage);


    /**
     * add a classInfoFilter, class in package which  any classinfofilter will be managed by {@link com.doubleysoft.kun.ioc.KunIoc}
     *
     * @param classInfoFilter
     */
    void addClassInfoFilter(ClassInfoFilter classInfoFilter);
}
