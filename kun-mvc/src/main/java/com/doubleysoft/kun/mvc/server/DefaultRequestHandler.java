package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.filter.HttpRequestFilter;
import com.doubleysoft.kun.mvc.server.model.BodyWriterChain;
import com.doubleysoft.kun.mvc.server.model.DefaultKunHttpResponse;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:17
 */
@RequiredArgsConstructor
@Slf4j
public class DefaultRequestHandler implements RequestHandler {
    private final HttpRequestFilter httpRequestFilter;
    private final BodyWriterChain   bodyWriterChain;

    @Override
    public KunHttpResponse handle(KunHttpRequest httpRequest) {
        DefaultKunHttpResponse response            = new DefaultKunHttpResponse();
        boolean                notExecuteByHandler = httpRequestFilter.beforeHandle(httpRequest, response);
        if (!notExecuteByHandler) {
            return response;
        }

        MethodInfo reqMethod = MvcContextHolder.getRouter().getReqHandler(httpRequest.reqURI());
        if (reqMethod == null) {
            log.error("Not found request path [{}] mapping", httpRequest.reqURI());
            response.setStatus(404);
            return response;
        }
        handle(httpRequest, response, MvcContextHolder.getKunContext(), reqMethod);
        return response;
    }

    private void handle(KunHttpRequest httpRequest, DefaultKunHttpResponse httpResponse, KunContext kunContext,
                        MethodInfo handlerMethod) {
        bodyWriterChain.writeResponse(httpRequest, httpResponse, kunContext, handlerMethod);
    }

}
