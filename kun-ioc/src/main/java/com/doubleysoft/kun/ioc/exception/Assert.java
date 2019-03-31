package com.doubleysoft.kun.ioc.exception;

/**
 * @author cupofish@gmail.com
 * 3/31/19 14:59
 */
public class Assert {
    public static void notNull(Object object) {
        if (object == null) {
            throw new StateException("object can't be null");
        }
    }
}
