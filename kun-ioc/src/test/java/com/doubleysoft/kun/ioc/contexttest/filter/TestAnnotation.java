package com.doubleysoft.kun.ioc.contexttest.filter;

import java.lang.annotation.*;

/**
 * @author cupofish@gmail.com
 * 3/24/19 17:03
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {
}
