package com.doubleysoft.kun.mvc.server.netty;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpUtil;

/**
 * @author cupofish@gmail.com
 * 3/23/19 16:05
 */
public class DefaultKunHttpRequest implements KunHttpRequest {
    private io.netty.handler.codec.http.HttpRequest nettyRequest;
    private String strContent;

    public DefaultKunHttpRequest(io.netty.handler.codec.http.HttpRequest nettyRequest) {
        this.nettyRequest = nettyRequest;
    }

    @Override
    public void appendContent(String content) {
        strContent = content;
    }

    @Override
    public String getContent() {
        return strContent;
    }

    @Override
    public boolean isKeepAlive() {
        return HttpUtil.isKeepAlive(nettyRequest);
    }

    @Override
    public String getReqURI() {
        if (HttpMethod.GET.equals(nettyRequest.method())) {
            String uri           = nettyRequest.uri();
            int    queryChartPos = uri.lastIndexOf("?");
            return uri.substring(0, queryChartPos == -1 ? uri.length() : queryChartPos);
        }
        return nettyRequest.uri();
    }

    @Override
    public String getReqURL() {
        return null;
    }

    @Override
    public String toString() {
        return "content:" + strContent;
    }
}
