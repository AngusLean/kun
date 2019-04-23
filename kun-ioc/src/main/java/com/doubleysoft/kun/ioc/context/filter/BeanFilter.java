package com.doubleysoft.kun.ioc.context.filter;

import com.doubleysoft.kun.ioc.context.BeanDefinition;

import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 14:57
 */
public interface BeanFilter {
    /**
     * filter beans
     *
     * @param beans
     * @return
     */
    List<BeanDefinition> filterBeans(List<BeanDefinition> beans);
}
