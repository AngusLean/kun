package com.doubleysoft.kun.ioc.scanner;

import com.doubleysoft.kun.ioc.context.ResourceInfo;

/**
 * @author cupofish@gmail.com
 * 3/24/19 16:28
 */
public interface ClassInfoFilter {
    /**
     * filter classes thich should be managed by ioc container
     *
     * @param resourceInfo
     * @return
     */
    boolean filterResourceClassInfo(ResourceInfo resourceInfo);
}
