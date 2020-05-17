package com.doubleysoft.kun.mvc.handler;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.server.RequestProcessor;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.doubleysoft.kun.mvc.server.Const.KEY_CONTENT_TYPE;

/**
 * @author cupofish@gmail.com
 * 8/9/19 22:34
 */
@Slf4j
public class HttpRequestHandlerChain {
    private List<RequestProcessor> defaultRequestProcessor;

    public HttpRequestHandlerChain() {
        defaultRequestProcessor = new ArrayList<RequestProcessor>() {{
            add(new CharBodyRequestProcessor());
        }};
    }

    public void handle(KunHttpRequest request, KunHttpResponse response, KunContext kunContext, MethodInfo handlerMethod) {
        ContentTypeEnum contentType = request.contentType();
        if (contentType == null) {
            log.warn("Illegal Content Type: {}", request.header(KEY_CONTENT_TYPE));
            response.setStatus(400);
            return;
        }
        for (RequestProcessor requestProcessor : defaultRequestProcessor) {
            if (requestProcessor.acceptRequest(contentType, request.method())) {
                requestProcessor.writeResponse(request, response, kunContext, handlerMethod);
                return;
            }
        }
        log.warn("Non Matched RequestProcessor With Request:[{}]", request);
        response.setStatus(200);
    }
}
