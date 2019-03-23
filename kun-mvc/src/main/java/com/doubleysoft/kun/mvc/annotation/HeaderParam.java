package com.doubleysoft.kun.mvc.annotation;

import java.lang.annotation.*;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HeaderParam {

    String value() default "";

    String defaultValue() default "";

}