package com.doubleysoft.kun.mvc.server.netty;

import com.doubleysoft.kun.mvc.helper.WebUtil;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MultivaluedMap;

/**
 * @author cupofish@gmail.com
 * 3/23/19 16:05
 */
@Slf4j
public class NettyKunHttpRequest implements KunHttpRequest {
    private io.netty.handler.codec.http.HttpRequest nettyRequest;
    private String strContent;

    public NettyKunHttpRequest(io.netty.handler.codec.http.HttpRequest nettyRequest) {
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
    public MultivaluedMap<String, Object> getReqParams() {
        return WebUtil.unwrapURIParams(nettyRequest.uri());
    }

    @Override
    public String toString() {
        return "NettyKunHttpRequest [content:" + strContent + "]";
    }
}
