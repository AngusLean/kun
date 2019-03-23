package com.doubleysoft.kun.mvc.annotation;


import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface URLPattern {

    /**
     * @return URL patterns
     */
    String[] values() default {"/*"};

}