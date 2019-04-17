package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import com.doubleysoft.kun.mvc.server.netty.NettyKunHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * @author cupofish@gmail.com
 * 4/7/19 16:09
 */
public class DefaultRequestHandlerTest {
    private RequestHandler requestHandler;

    @Before
    public void init() {
        requestHandler = new DefaultRequestHandler();
    }

    @Test
    public void handle() {
        MethodInfo methodInfo = MethodInfo.builder()
//                .beanDifination()
                .build();

        Router router = new Router();
        Router spy    = Mockito.spy(router);

        KunContext kunContext = Mockito.mock(KunContext.class);
        MvcContextHolder.init(kunContext, spy);

        doReturn(methodInfo).when(spy).getReqHandler(any());

        HttpRequest     nettyRequest = Mockito.mock(HttpRequest.class);
        KunHttpRequest  httpRequest  = new NettyKunHttpRequest(nettyRequest);
        KunHttpResponse response     = requestHandler.handle(httpRequest);
    }
}