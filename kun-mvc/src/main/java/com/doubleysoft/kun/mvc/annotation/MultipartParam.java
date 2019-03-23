package com.doubleysoft.kun.mvc.annotation;

import java.lang.annotation.*;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultipartParam {

    String value() default "file";

}