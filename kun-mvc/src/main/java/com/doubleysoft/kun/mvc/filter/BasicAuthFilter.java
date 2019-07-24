package com.doubleysoft.kun.mvc.filter;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author cupofish@gmail.com
 * 7/24/19 23:00
 */
public class BasicAuthFilter implements HttpRequestFilter {
    private static final String AUTH_KEY = "";

    @Override
    public boolean beforeHandle(KunHttpRequest httpRequest, KunHttpResponse response) {
        String authVal = httpRequest.getHeader(AUTH_KEY);
        if (authVal == null) {
            return true;
        }

        return true;
    }
}
