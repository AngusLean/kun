package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.exception.Assert;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cupofish@gmail.com
 * 3/31/19 14:58
 */
@RequiredArgsConstructor
public class BeanAnnotationFilter implements BeanFilter {
    private final List<Class<? extends Annotation>> annotations;


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
