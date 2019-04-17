package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.Ioc;
import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.KunIoc;
import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import com.doubleysoft.kun.mvc.server.netty.NettyKunHttpRequest;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.Path;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class RequestHandlerTest {
    private static final String TEST_RESPONSE_STR = "hello world";
    private static final String TEST_REQ_CONTENT  = "PARAM_中文";
    private static final String TEST_REQ_PATH     = "/index";

    private KunContext kunContext;

    @Before
    public void init() {
        kunContext = new KunContext(RequestHandlerTest.class.getName());
        MvcContextHolder.init(kunContext, new Router());
    }

    @Test
    public void handle() {
        RequestHandler requestHandler = new DefaultRequestHandler();
        Ioc            ioc            = new KunIoc();
        BeanDifination beanDifination = new BeanDifination(ioc);
        beanDifination.setKlass(RequestHandlerTestController.class);
        MethodInfo methodInfo = MethodInfo.builder().beanDifination(beanDifination).methodName("index").build();
        MvcContextHolder.getRouter().addRoute(TEST_REQ_PATH, methodInfo);

        KunHttpRequest httpRequest = new NettyKunHttpRequest(new DefaultHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, TEST_REQ_PATH));
        httpRequest.appendContent(TEST_REQ_CONTENT);
        KunHttpResponse httpResponse = requestHandler.handle(httpRequest);
        Assert.assertEquals(TEST_RESPONSE_STR + TEST_REQ_CONTENT, httpResponse.getContent());
    }


    public class RequestHandlerTestController {

        @Path(TEST_REQ_PATH)
        public String index(String param) {
            return TEST_RESPONSE_STR + param;
        }

    }
}