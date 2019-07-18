package com.doubleysoft.kun.mvc.filter;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public interface HttpRequestFilter {
    /**
     * run filter if  {@see shouldFilter} return true
     *
     * @param httpRequest
     * @param response
     */
    void filter(KunHttpRequest httpRequest, KunHttpResponse response);

    /**
     * determine whether this filter should be run
     *
     * @param httpRequest
     * @param response
     * @return
     */
    boolean shouldFilter(KunHttpRequest httpRequest, KunHttpResponse response);
}
