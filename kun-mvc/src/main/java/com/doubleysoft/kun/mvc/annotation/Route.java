package com.doubleysoft.kun.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {

    String value() default "";

    /**
     * @return route description
     */
    String method() default "";
}
