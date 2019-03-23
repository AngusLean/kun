package com.doubleysoft.kun.mvc.server.netty.handler;

import com.doubleysoft.kun.mvc.server.model.HttpRequestFactory;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author cupofish@gmail.com
 * 3/23/19 16:07
 */
@Slf4j
public class MergeHttpRequestHandler extends SimpleChannelInboundHandler<HttpObject> {

    private KunHttpRequest kunHttpRequest;

    static boolean isResetByPeer(Throwable e) {
        if (null != e.getMessage() &&
                e.getMessage().contains("Connection reset by peer")) {
            return true;
        }
        return false;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        if (msg instanceof io.netty.handler.codec.http.HttpRequest) {
            kunHttpRequest = HttpRequestFactory.nettyRequest((io.netty.handler.codec.http.HttpRequest) msg);
            return;
        }
        if (null != kunHttpRequest && msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            String      content     = httpContent.content().toString(CharsetUtil.UTF_8);
            kunHttpRequest.appendContent(content);
        }
        if (msg instanceof LastHttpContent) {
            if (null != kunHttpRequest) {
                ctx.fireChannelRead(kunHttpRequest);
            } else {
                ctx.fireChannelRead(msg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage(), cause);
        if (!isResetByPeer(cause)) {
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.valueOf(500));
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }
}