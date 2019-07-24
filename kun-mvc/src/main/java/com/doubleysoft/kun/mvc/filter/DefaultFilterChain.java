package com.doubleysoft.kun.mvc.filter;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

import java.util.List;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class DefaultFilterChain implements HttpRequestFilter {
    private List<HttpRequestFilter> filters;

    public DefaultFilterChain() {

    }

    public void setFilters(List<HttpRequestFilter> filters) {
        this.filters.addAll(filters);
    }

    @Override
    public boolean beforeHandle(KunHttpRequest httpRequest, KunHttpResponse response) {
        return true;
    }

    @Override
    public boolean afterHandle(KunHttpRequest httpRequest, KunHttpResponse response) {
        return true;
    }
}
