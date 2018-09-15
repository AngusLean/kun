package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;

import javax.annotation.Resource;

/**
 * Created by anguslean
 * 18-9-9 下午7:03
 */
public interface JavaxAnotationResourcesTraits {
    default boolean filterClassInfoTrait(ClassInfo classInfo) {
        return !classInfo.getKlass().isAnnotationPresent(Resource.class);
    }
}