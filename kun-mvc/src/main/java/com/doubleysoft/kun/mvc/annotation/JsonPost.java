package com.doubleysoft.kun.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Route(method = "")
public @interface JsonPost {

    String[] value() default "/";

    /**
     * @return route description
     */
    String description() default "";
}