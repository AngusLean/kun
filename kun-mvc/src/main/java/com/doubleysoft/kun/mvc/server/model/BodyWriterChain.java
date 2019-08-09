package com.doubleysoft.kun.mvc.server.model;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.server.BodyWritter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.doubleysoft.kun.mvc.server.Const.KEY_CONTENT_TYPE;

/**
 * @author cupofish@gmail.com
 * 8/9/19 22:34
 */
@Slf4j
public class BodyWriterChain {
    private List<BodyWritter> defaultBodyWritter;

    public BodyWriterChain() {
        defaultBodyWritter = new ArrayList<BodyWritter>() {{
            add(new CharBodyWriter());
        }};
    }

    public void writeResponse(KunHttpRequest request, KunHttpResponse response, KunContext kunContext, MethodInfo handlerMethod) {
        ContentTypeEnum contentType = request.contentType();
        if (contentType == null) {
            log.warn("Illegal Content Type: {}", request.header(KEY_CONTENT_TYPE));
            response.setStatus(400);
            return;
        }
        for (BodyWritter bodyWritter : defaultBodyWritter) {
            if (bodyWritter.acceptRequest(contentType, request.method())) {
                bodyWritter.writeResponse(request, response, kunContext, handlerMethod);
                return;
            }
        }
        log.warn("Non Matched BodyWritter With Request:[{}]", request);
        response.setStatus(200);
    }
}
