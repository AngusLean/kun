package com.doubleysoft.kun.mvc.filter;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class DefaultFilterChain implements HttpRequestFilter {
    @Override
    public void filter(KunHttpRequest httpRequest, KunHttpResponse response) {

    }

    @Override
    public boolean shouldFilter(KunHttpRequest httpRequest, KunHttpResponse response) {
        return true;
    }
}
