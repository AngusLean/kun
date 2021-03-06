package com.doubleysoft.kun.mvc.filter;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
public interface HttpRequestFilter {
    /**
     * run beforeHandle if  {@see afterHandle} return true
     *
     * @param httpRequest
     * @param response
     * @return return true if execute next route or actual handle method, or else return to client by current response
     */
    default boolean beforeHandle(KunHttpRequest httpRequest, KunHttpResponse response) {
        return true;
    }

    /**
     * determine whether this beforeHandle should be run
     *
     * @param httpRequest
     * @param response
     * @return return true if execute next route, or else return to client by current response
     */
    default boolean afterHandle(KunHttpRequest httpRequest, KunHttpResponse response) {
        return true;
    }

    default boolean afterException(KunHttpRequest httpRequest, Exception e) {
        return true;
    }
}
