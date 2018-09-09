package com.doubleysoft.kun.context;

import com.doubleysoft.kun.exception.StateException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by anguslean
 * 18-9-9 下午5:29
 */
@Data
@Builder
@Slf4j
public class ClassInfo {
    private String className;

    public Class getKlass() {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("fail in find class of: {}", className, e);
            throw new StateException("fail in find class");
        }
    }
}
