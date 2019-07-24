package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.context.MethodInfo;
import lombok.Builder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cupofish@gmail.com
 * 3/24/19 17:42
 */
public class Router {
    private ConcurrentHashMap<RouterKey, MethodInfo> newRouterCache;

    public Router() {
        newRouterCache = new ConcurrentHashMap<>();
    }

    public void addRoute(RouterKey key, MethodInfo methodInfo) {
        newRouterCache.putIfAbsent(key, methodInfo);
    }

    public MethodInfo getReqHandler(String reqPath) {
        return newRouterCache.get(RouterKey.builder().absPath(reqPath).build());
    }

    @Builder
    public static class RouterKey {
        private String absPath;

        private String httpMethod;

        //todo performance change
        @Override
        public int hashCode() {
            return httpMethod == null ? absPath.hashCode() : (httpMethod + absPath).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof RouterKey)) {
                return false;
            }
            return this.hashCode() == obj.hashCode();
        }
    }
}
