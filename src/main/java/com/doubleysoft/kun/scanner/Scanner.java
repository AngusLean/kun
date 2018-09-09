package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;

import java.util.Set;

/**
 * Created by anguslean on 18-9-9.
 */
public interface Scanner {
    Set<ClassInfo> scan(String packages);
}
