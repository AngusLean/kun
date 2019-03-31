package com.doubleysoft.kun.ioc.context.filter;

import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 15:55
 */
@RequiredArgsConstructor
public abstract class AbstractAnnotationFilter implements BeanFilter {
    protected final List<Class<? extends Annotation>> annotations;
}
