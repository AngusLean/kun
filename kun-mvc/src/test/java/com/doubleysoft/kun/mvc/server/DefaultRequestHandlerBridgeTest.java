package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.KunMvcContext;
import com.doubleysoft.kun.mvc.filter.DefaultFilterChain;
import com.doubleysoft.kun.mvc.handler.DefaultRequestHandlerBridge;
import com.doubleysoft.kun.mvc.handler.HttpRequestHandlerChain;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import com.doubleysoft.kun.mvc.server.netty.NettyKunHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * @author cupofish@gmail.com
 * 4/7/19 16:09
 */
public class DefaultRequestHandlerBridgeTest {
    private RequestHandler requestHandler;

    @Before
    public void init() {
        requestHandler = new DefaultRequestHandlerBridge(new DefaultFilterChain(), new HttpRequestHandlerChain());
    }

    @Ignore
    public void handle() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClazz(DefaultRequestHandlerTestDemo.class);

        MethodInfo methodInfo = MethodInfo.builder()
                .beanDefinition(beanDefinition)
                .methodName("hello")
                .build();

        Router router = new Router();
        Router spy    = Mockito.spy(router);

        KunContext kunContext = Mockito.mock(KunMvcContext.class);
        when(kunContext.getBean("com.doubleysoft.kun.mvc.server.DefaultRequestHandlerBridgeTest$DefaultRequestHandlerTestDemo")).thenReturn(new DefaultRequestHandlerTestDemo());
        MvcContextHolder.init(kunContext, spy);

        doReturn(methodInfo).when(spy).getReqHandler(any());

        HttpRequest     nettyRequest = Mockito.mock(HttpRequest.class);
        when(nettyRequest.method()).thenReturn(HttpMethod.GET);
        when(nettyRequest.uri()).thenReturn("test.com?name=teststring&age=14");
        KunHttpRequest  httpRequest  = new NettyKunHttpRequest(nettyRequest);
        KunHttpResponse response     = requestHandler.handle(httpRequest);
        Assert.assertEquals("teststring", response.getContent());
    }

    public class DefaultRequestHandlerTestDemo {
        public String hello(String name, int age) {
            return name;
        }
    }


}