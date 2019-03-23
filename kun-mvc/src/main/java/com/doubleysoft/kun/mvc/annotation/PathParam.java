package com.doubleysoft.kun.mvc.annotation;

import java.lang.annotation.*;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathParam {

    String name() default "";

    String defaultValue() default "";

    /**
     * @return route description
     */
    String description() default "";

}