package com.doubleysoft.kun.mvc.server.model;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.helper.DateUtil;
import com.doubleysoft.kun.mvc.helper.MvcHelper;
import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.http.HttpMethodEnum;
import com.doubleysoft.kun.mvc.server.BodyWritter;
import lombok.extern.slf4j.Slf4j;

import static com.doubleysoft.kun.mvc.server.Const.*;

/**
 * @author cupofish@gmail.com
 * 8/9/19 22:20
 */
@Slf4j
public class CharBodyWriter implements BodyWritter {
    @Override
    public boolean acceptRequest(ContentTypeEnum contentType, HttpMethodEnum httpMethod) {
        return (contentType == ContentTypeEnum.APPLICATION_JSON || contentType == ContentTypeEnum.TEXT_PLAIN)
                && (httpMethod == HttpMethodEnum.POST || httpMethod == HttpMethodEnum.GET);
    }

    @Override
    public void writeResponse(KunHttpRequest request, KunHttpResponse response, KunContext kunContext, MethodInfo handlerMethod) {
        Object[] callParams = MvcHelper.getMethodCallParams(request, handlerMethod);

        //response content
        Object responseObj = handlerMethod.execute(kunContext.getBean(handlerMethod.getBeanName()), callParams);
        if (responseObj != null) {
            response.setContent(responseObj);
        }

        //response headers
        setResponseHeaders(request, response, handlerMethod);
    }

    private void setResponseHeaders(KunHttpRequest httpRequest, KunHttpResponse httpResponse, MethodInfo handlerMethod) {
        httpResponse.setStatus(200);
        httpResponse.getHeaders().put(KEY_DATE, DateUtil.gmtDate());
        httpResponse.getHeaders().put(KEY_LAST_MODIFIED, DateUtil.gmtDate());
        httpResponse.getHeaders().put(KEY_CONTENT_TYPE, CONTENT_TYPE_JSON);
        if (httpRequest.isKeepAlive()) {
            httpResponse.getHeaders().put(KEY_CONNECTION, KEY_KEEP_ALIVE);
        }
    }
}
