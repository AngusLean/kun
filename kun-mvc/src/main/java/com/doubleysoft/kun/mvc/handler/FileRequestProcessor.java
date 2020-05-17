package com.doubleysoft.kun.mvc.handler;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.http.HttpMethodEnum;
import com.doubleysoft.kun.mvc.server.RequestProcessor;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

public class FileRequestProcessor implements RequestProcessor {
    @Override
    public boolean acceptRequest(ContentTypeEnum contentType, HttpMethodEnum httpMethod) {
        return false;
    }

    @Override
    public void writeResponse(KunHttpRequest request, KunHttpResponse response, KunContext kunContext, MethodInfo handlerMethod) {

    }
}
