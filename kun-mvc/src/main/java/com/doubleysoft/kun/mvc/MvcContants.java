package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.context.filter.BeanFilter;
import com.doubleysoft.kun.ioc.context.filter.BeanMethodAnnotationFilter;
import com.doubleysoft.kun.mvc.helper.AnnotationHelper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 16:04
 */
public class MvcContants {
    @Getter
    private static final List<BeanFilter> webRequestBeanFilters;

    static {
        webRequestBeanFilters = new ArrayList<>();
        webRequestBeanFilters.add(new BeanMethodAnnotationFilter(AnnotationHelper.getWebReqAnno(), true));
    }
}
