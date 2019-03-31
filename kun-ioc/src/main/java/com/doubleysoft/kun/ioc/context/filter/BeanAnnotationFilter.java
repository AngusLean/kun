package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.exception.Assert;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cupofish@gmail.com
 * 3/31/19 14:58
 */
public class BeanAnnotationFilter extends AbstractAnnotationFilter {

    public BeanAnnotationFilter(List<Class<? extends Annotation>> annotations) {
        super(annotations);
    }

    @Override
    public List<BeanDifination> filterBeans(List<BeanDifination> beans) {
        Assert.notNull(beans);
        return beans.stream()
                .filter(row ->
                        !annotations.stream().anyMatch(annotation ->
                                !row.getKlass().isAnnotationPresent(annotation)))
                .collect(Collectors.toList());
    }
}
