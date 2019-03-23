package com.doubleysoft.kun.mvc.annotation;


import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Path {

    /**
     * @return namespace
     */
    String value() default "/";

    /**
     * @return route suffix
     */
    String suffix() default "";

    /**
     * @return is restful api
     */
    boolean restful() default false;

    /**
     * @return path description
     */
    String description() default "";
}