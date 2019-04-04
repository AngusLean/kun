package com.doubleysoft.kun.mvc.server.netty;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MultivaluedHashMap;
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
        MultivaluedMap<String, Object> result = new MultivaluedHashMap<>();
        if (HttpMethod.GET.equals(nettyRequest.method())) {
            String uri           = nettyRequest.uri();
            int    queryChartPos = uri.lastIndexOf("?");
            if (queryChartPos != -1) {
                String queryParam = uri.substring(queryChartPos + 1, uri.length());
                for (String param : queryParam.split("&")) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length != 2) {
                        log.warn("request param:{} is illegal ", keyValue);
                    } else {
                        result.add(keyValue[0], keyValue[1]);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "content:" + strContent;
    }
}
