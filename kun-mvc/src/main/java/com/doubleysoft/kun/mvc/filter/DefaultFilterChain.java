package com.doubleysoft.kun.mvc.filter;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
@Slf4j
public class DefaultFilterChain implements HttpRequestFilter {
    private Set<HttpRequestFilter> filters;

    public DefaultFilterChain() {
        filters = new HashSet<>();
    }

    public void setFilters(List<HttpRequestFilter> filters) {
        this.filters.addAll(filters);
    }

    @Override
    public boolean beforeHandle(KunHttpRequest httpRequest, KunHttpResponse response) {
        if (this.filters.isEmpty()) {
            return true;
        }
        Optional<HttpRequestFilter> illegalFilter = this.filters.stream()
                .filter(row -> !row.beforeHandle(httpRequest, response))
                .findAny();
        if (illegalFilter.isPresent()) {
            log.debug("KunMvc Filter :{} return false, then stop default request handler flow", illegalFilter.get());
            return false;
        }
        return true;
    }

    @Override
    public boolean afterHandle(KunHttpRequest httpRequest, KunHttpResponse response) {
        return true;
    }
}
