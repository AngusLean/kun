package com.doubleysoft.kun.context;

import com.doubleysoft.kun.exception.StateException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
@Slf4j
public class BeanDifination<T> {
    @Setter
    private Class<T> klass;

    private T instance;

    public <T> T getInstance() {
        if (instance == null) {
            try {
                instance = klass.newInstance();
            } catch (InstantiationException e) {
                log.error("error in init class {}", klass);
                throw new StateException("error in init bean " + klass.getName());
            } catch (IllegalAccessException e) {
                log.error("error in init class {}, IllegalAccess", klass);
                throw new StateException("error in init bean " + klass.getName() + ", illegal access");
            }
        }
        return (T) instance;
    }
}
