package com.doubleysoft.kun.mvc.handler;

import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.http.HttpMethodEnum;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cupofish@gmail.com
 * 8/9/19 22:20
 */
@Slf4j
public class CharBodyRequestProcessor extends AbstractRequestProcessor {
    @Override
    public boolean acceptRequest(ContentTypeEnum contentType, HttpMethodEnum httpMethod) {
        return (contentType == ContentTypeEnum.APPLICATION_JSON || contentType == ContentTypeEnum.TEXT_PLAIN)
                && (httpMethod == HttpMethodEnum.POST || httpMethod == HttpMethodEnum.GET);
    }

    @Override
    protected void doWriteResponse(KunHttpRequest request, KunHttpResponse response, Object methodResponse) {
        response.setContent(methodResponse);
    }

}
