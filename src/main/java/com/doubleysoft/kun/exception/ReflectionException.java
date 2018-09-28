package com.doubleysoft.kun.exception;

/**
 * @author anguslean
 * 18-9-28 下午6:05
 * @since 0.0.1
 */
public class ReflectionException extends StateException {
    private Exception exception;

    public ReflectionException(Exception e, String message) {
        super(message);
        this.exception = e;
    }
}
