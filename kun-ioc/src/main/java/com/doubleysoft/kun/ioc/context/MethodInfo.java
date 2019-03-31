package com.doubleysoft.kun.ioc.context;

import com.doubleysoft.kun.ioc.exception.StateException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author cupofish@gmail.com
 * 3/27/19 22:36
 */
@RequiredArgsConstructor
@Slf4j
@Builder
public class MethodInfo {
    private final BeanDifination beanDifination;

    private final String methodName;

    public Method getMethod() {
        try {
            Method method = beanDifination.getKlass().getMethod(methodName);
            return method;
        } catch (NoSuchMethodException e) {
            log.error("error in call mathod :{} of class:{}", methodName, beanDifination.getKlass(), e);
            throw new StateException("error in get method of class" + beanDifination.getKlass());
        }
    }
}
