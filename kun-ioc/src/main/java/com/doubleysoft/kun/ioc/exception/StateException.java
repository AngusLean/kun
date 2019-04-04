package com.doubleysoft.kun.ioc.exception;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
public class StateException extends RuntimeException {
    private Exception e;

    public StateException(String message) {
        super(message);
    }

    public StateException(String message, Exception e) {
        super(message);
        this.e = e;
    }
}
