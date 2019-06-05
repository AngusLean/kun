package com.doubleysoft.kun.ioc.util;

import com.doubleysoft.kun.ioc.context.Depend;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author cupofish@gmail.com
 * 5/27/19 21:29
 */
@Slf4j
public class BeanUtil {

    public static Field getField(Class<?> clazz, Depend depend) {
        try {
            return clazz.getDeclaredField(depend.getSimpleName());
        } catch (Exception e) {
            log.debug("Error in get depend:{} of class:{}", depend, clazz, e);
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field fd : fields) {
            if (depend.getName().equals(fd.getType().getName())) {
                return fd;
            }
        }
        return null;
    }

}
