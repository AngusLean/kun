package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import lombok.Getter;

/**
 * @author cupofish@gmail.com
 * 4/1/19 23:10
 */
public class MvcContextHolder {
    @Getter
    private static Router router;

    @Getter
    private static KunContext kunContext;

    public static void init(KunContext context) {
        router = new Router();
        kunContext = context;
    }
}
