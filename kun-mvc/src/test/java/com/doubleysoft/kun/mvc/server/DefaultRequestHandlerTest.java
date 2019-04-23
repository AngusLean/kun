package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.KunMvcContext;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import com.doubleysoft.kun.mvc.server.netty.NettyKunHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
        BeanDefinition beanDefinition = new BeanDefinition(null);
        beanDefinition.setKlass(DefaultRequestHandlerTestDemo.class);

        MethodInfo methodInfo = MethodInfo.builder()
                .beanDefinition(beanDefinition)
                .methodName("hello")
                .build();

        Router router = new Router();
        Router spy    = Mockito.spy(router);

        KunContext kunContext = Mockito.mock(KunMvcContext.class);
        when(kunContext.getBean(anyString())).thenReturn(new DefaultRequestHandlerTestDemo());
        MvcContextHolder.init(kunContext, spy);

        doReturn(methodInfo).when(spy).getReqHandler(any());

        HttpRequest     nettyRequest = Mockito.mock(HttpRequest.class);
        when(nettyRequest.method()).thenReturn(HttpMethod.GET);
        when(nettyRequest.uri()).thenReturn("test.com?name=zhagnsan&age=14");
        KunHttpRequest  httpRequest  = new NettyKunHttpRequest(nettyRequest);
        KunHttpResponse response     = requestHandler.handle(httpRequest);
    }

    public class DefaultRequestHandlerTestDemo {
        public String hello(String name, int age) {
            return name;
        }
    }
}