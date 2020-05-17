package com.doubleysoft.kun.mvc.handler;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.helper.DateUtil;
import com.doubleysoft.kun.mvc.helper.MvcHelper;
import com.doubleysoft.kun.mvc.server.RequestProcessor;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

import static com.doubleysoft.kun.mvc.server.Const.*;

public abstract class AbstractRequestProcessor implements RequestProcessor {
    @Override
    public void writeResponse(KunHttpRequest request, KunHttpResponse response, KunContext kunContext, MethodInfo handlerMethod) {
        Object[] callParams = MvcHelper.getMethodCallParams(request, handlerMethod);
        Object handlerObj = kunContext.getBean(handlerMethod.getBeanName());
        //response content
        Object responseObj = handlerMethod.execute(handlerObj, callParams);
        if (responseObj != null) {
            doWriteResponse(request, response, responseObj);
        }

        //response headers
        setResponseHeaders(request, response, handlerMethod);
    }


    abstract protected void doWriteResponse(KunHttpRequest request, KunHttpResponse response, Object methodResponse);

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
