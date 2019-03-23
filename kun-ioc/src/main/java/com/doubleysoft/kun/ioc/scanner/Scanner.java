package com.doubleysoft.kun.ioc.scanner;

import com.doubleysoft.kun.ioc.context.ClassInfo;

import java.util.Set;

/**
 * Created by anguslean on 18-9-9.
 */
public interface Scanner {
    Set<ClassInfo> scan(String packages);
}
