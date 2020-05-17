package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.http.HttpMethodEnum;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author cupofish@gmail.com
 * 8/9/19 22:16
 */
public interface RequestProcessor {
    /**
     * detect whether request can be processed by this writer
     *
     * @param contentType
     * @param httpMethod
     */
    boolean acceptRequest(ContentTypeEnum contentType, HttpMethodEnum httpMethod);

    /**
     * write response to KunHttpResponse
     *
     * @param request
     * @param response
     */
    void writeResponse(KunHttpRequest request, KunHttpResponse response, KunContext kunContext, MethodInfo handlerMethod);
}
