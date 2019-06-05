package com.doubleysoft.kun.mvc.server.netty.handler;

import com.doubleysoft.kun.mvc.server.RequestHandler;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.util.CharsetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author cupofish@gmail.com
 * 3/23/19 15:32
 */
@ChannelHandler.Sharable
@Slf4j
@RequiredArgsConstructor
public class KunHttpNettyHandler extends SimpleChannelInboundHandler<KunHttpRequest> {
    private final RequestHandler requestHandler;

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, KunHttpRequest httpRequest) {
        if (!writeResponse(httpRequest, ctx)) {
            // If keep-alive is off, close the connection once the content is fully written.
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("error in handle http request", cause);
        ctx.close();
    }

    private boolean writeResponse(KunHttpRequest httpRequest, ChannelHandlerContext ctx) {
//        boolean isKeepAlive = httpRequest.isKeepAlive();
        KunHttpResponse httpResponse = requestHandler.handle(httpRequest);

        boolean isKeepAlive = false;
        FullHttpResponse fullHttpResponse = setNettyResponse(httpResponse);
        ctx.write(fullHttpResponse);
        return isKeepAlive;
    }

    private FullHttpResponse setNettyResponse(KunHttpResponse httpResponse){
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
                Unpooled.copiedBuffer(httpResponse.getContent(), CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return response;
    }
}
