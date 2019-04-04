package com.doubleysoft.kun.mvc.server.model;

import com.doubleysoft.kun.mvc.server.netty.NettyKunHttpRequest;

/**
 * @author cupofish@gmail.com
 * 3/23/19 16:04
 */
public class HttpRequestFactory {
    public static KunHttpRequest nettyRequest(io.netty.handler.codec.http.HttpRequest nettyHttpRequest) {
        return new NettyKunHttpRequest(nettyHttpRequest);
    }
}
