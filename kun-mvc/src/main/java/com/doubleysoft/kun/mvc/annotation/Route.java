package com.doubleysoft.kun.mvc.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Route {

    /**
     * @return Request url
     */
    String[] value() default "/";


    /**
     * @return Route description
     */
    String description() default "";
}