package com.doubleysoft.kun.mvc.helper;

import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.CookieParam;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class MvcHelperTest {
    private KunHttpRequest kunHttpRequest;
    private MethodInfo methodInfo;

    @Before
    public void init() {
        methodInfo = Mockito.mock(MethodInfo.class);
        kunHttpRequest = Mockito.spy(KunHttpRequest.class);
    }

    @Test
    public void getMethodCallParamsBasic() throws NoSuchMethodException {
        Method userMethod = MethodCallParamBasicTest.class.getMethod("user", int.class, String.class);
        Object[] target = new Object[]{23, "Rust"};
        MultivaluedMap multivaluedMap = new MultivaluedHashMap();
        multivaluedMap.add("age", "23");
        multivaluedMap.add("name", "Rust");

        Mockito.when(methodInfo.isDecodeReqParam()).thenReturn(true);
        Mockito.when(methodInfo.getMethod()).thenReturn(userMethod);

        Mockito.when(kunHttpRequest.getReqParams()).thenReturn(multivaluedMap);
        Object[] methodCallParams = MvcHelper.getMethodCallParams(kunHttpRequest, methodInfo);
        Assert.assertArrayEquals(target, methodCallParams);
    }

    @Test
    public void getMethodCallParamsCookie() throws NoSuchMethodException {
        Method userMethod = MethodCallParamBasicTest.class.getMethod("content", Float.class, String.class);
        Object[] target = new Object[]{Float.valueOf(23), "TEST_TOKEN_VALUE"};
        MultivaluedMap multivaluedMap = new MultivaluedHashMap();
        multivaluedMap.add("age", "23");
        multivaluedMap.add("token", "TEST_TOKEN_VALUE_URL");

        javax.ws.rs.core.Cookie cookie = new javax.ws.rs.core.Cookie("token", "TEST_TOKEN_VALUE");

        Mockito.when(methodInfo.isDecodeReqParam()).thenReturn(true);
        Mockito.when(methodInfo.getMethod()).thenReturn(userMethod);

        Mockito.when(kunHttpRequest.getReqParams()).thenReturn(multivaluedMap);
        Mockito.when(kunHttpRequest.getCookie(any())).thenReturn(cookie);

        Object[] methodCallParams = MvcHelper.getMethodCallParams(kunHttpRequest, methodInfo);
        Assert.assertArrayEquals(target, methodCallParams);
    }

    public static class MethodCallParamBasicTest {

        public void user(int age, String name) {

        }

        public void content(Float age, @CookieParam(value = "token") String token) {

        }
    }
}