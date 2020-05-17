package com.doubleysoft.kun.mvc.server.model;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.http.HttpMethodEnum;
import com.doubleysoft.kun.mvc.server.BodyWritter;

public class RedirectBodyWriter implements BodyWritter {
    @Override
    public boolean acceptRequest(ContentTypeEnum contentType, HttpMethodEnum httpMethod) {
        return false;
    }

    @Override
    public void writeResponse(KunHttpRequest request, KunHttpResponse response, KunContext kunContext, MethodInfo handlerMethod) {

    }
}
