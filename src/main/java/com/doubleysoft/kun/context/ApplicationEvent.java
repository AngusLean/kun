package com.doubleysoft.kun.context;

import lombok.Getter;

import java.util.EventObject;
import java.util.Scanner;

/**
 * Created by anguslean
 * 18-9-23 下午4:12
 */
public class ApplicationEvent extends EventObject {
    @Getter
    private final long timestamp;

    public ApplicationEvent(Object source) {
        super(source);
        timestamp = System.currentTimeMillis();
    }
}
