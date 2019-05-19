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
    private final BeanDefinition beanDefinition;

    private final String methodName;

    public Method getMethod() {
        Method[] methods = beanDefinition.getClazz().getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        throw new StateException("error in get method of class" + beanDefinition.getClazz());
    }

    public Object execute(Object target, Object... params) {
        try {
            Method method     = getMethod();
            int    paramCount = method.getParameterCount();
            if (params.length != paramCount) {
                log.warn("call method :{}, request params:{}, but required param count is {}", method.getName(), params.length, paramCount);
                return method.invoke(target, new Object[paramCount]);
            }
            return method.invoke(target, params);
        } catch (Exception e) {
            log.error("fail in call method " + methodName, e);
            throw new StateException("fail in call method " + methodName);
        }
    }

    public boolean isDecodeReqParam() {
        return true;
    }

    public String getBeanName() {
        return beanDefinition.getClassInfo().getClassName();
    }

}
