package com.doubleysoft.kun.mvc.server.netty.handler;

import com.doubleysoft.kun.mvc.server.RequestHandler;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author cupofish@gmail.com
 * 3/23/19 15:32
 * Wrap KunHttpResponse to netty httpresponse way
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
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("error in handle http request", cause);
        setCommonResponse(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        ctx.flush();
    }

    private boolean writeResponse(KunHttpRequest httpRequest, ChannelHandlerContext ctx) {
        boolean keepAlive = httpRequest.isKeepAlive();
        try {
            KunHttpResponse httpResponse = requestHandler.handle(httpRequest);
            HttpResponse fullHttpResponse = setNettyResponse(httpResponse);
            //netty4 require set content-length, or else it will not send response,
//            fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
            //set keep-alive header
            if (keepAlive) {
                fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(fullHttpResponse);
        } catch (Exception e) {
            log.error("error in handle response in Netty", e);
            setCommonResponse(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return keepAlive;
    }

    private HttpResponse setNettyResponse(KunHttpResponse httpResponse) {
        HttpResponse response;
        if (httpResponse.getStatus() == OK.code()) {
            if (httpResponse.getContent() == null) {
                response = new DefaultHttpResponse(HTTP_1_1, OK);
                response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, 0);
            } else {
                response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(httpResponse.getContent(), CharsetUtil.UTF_8));
                response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.getContent().getBytes(CharsetUtil.UTF_8).length);
            }
        } else {
            response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.valueOf(httpResponse.getStatus()));
        }
        httpResponse.getHeaders().keySet().forEach(row -> response.headers().set(row, httpResponse.getHeaders().get(row)));
        return response;
    }


    private void setCommonResponse(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status);
        ctx.write(response);
    }

}
