package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.context.filter.BeanFilter;
import com.doubleysoft.kun.ioc.context.filter.BeanMethodAnnotationFilter;
import com.doubleysoft.kun.mvc.annotation.JsonPath;
import lombok.Getter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 16:04
 */
public class MvcContants {
    @Getter
    private static List<Class<? extends Annotation>> defaultReqAnnotations;

    @Getter
    private static final List<BeanFilter> webRequestBeanFilters;

    static {
        webRequestBeanFilters = new ArrayList<>();
        defaultReqAnnotations = Arrays.asList(Path.class, ApplicationPath.class, JsonPath.class);
        webRequestBeanFilters.add(new BeanMethodAnnotationFilter(defaultReqAnnotations, true));
    }
}
