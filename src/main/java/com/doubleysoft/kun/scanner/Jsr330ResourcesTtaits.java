package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;

import javax.annotation.Resource;

/**
 * Created by anguslean
 * 18-9-15 下午5:47
 */
public interface Jsr330ResourcesTtaits {
    default boolean filterClassInfoTrait(ClassInfo classInfo) {
        return !classInfo.getKlass().isAnnotationPresent(Resource.class);
    }
}
