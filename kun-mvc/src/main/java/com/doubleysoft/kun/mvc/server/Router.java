package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.context.MethodInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cupofish@gmail.com
 * 3/24/19 17:42
 */
public class Router {
    private ConcurrentHashMap<String, MethodInfo> routerCache = new ConcurrentHashMap<String, MethodInfo>();

    public void addRoute(String key, MethodInfo methodInfo) {
        routerCache.putIfAbsent(key, methodInfo);
    }

    public MethodInfo getReqHandler(String reqPath) {
        return routerCache.get(reqPath);
    }
}
