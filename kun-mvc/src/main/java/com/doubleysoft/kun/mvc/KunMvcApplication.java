package com.doubleysoft.kun.mvc;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class KunMvcApplication {
    public static void boot(Class clazz) {
        KunMvcBootstrap.boot(clazz);
    }
}
