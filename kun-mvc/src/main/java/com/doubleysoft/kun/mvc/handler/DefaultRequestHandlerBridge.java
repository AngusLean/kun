package com.doubleysoft.kun.mvc.handler;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.filter.HttpRequestFilter;
import com.doubleysoft.kun.mvc.server.MvcContextHolder;
import com.doubleysoft.kun.mvc.server.RequestHandler;
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
public class DefaultRequestHandlerBridge implements RequestHandler {
    private final HttpRequestFilter httpRequestFilter;
    private final HttpRequestHandlerChain httpRequestHandlerChain;

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

        try {
            handle(httpRequest, response, MvcContextHolder.getKunContext(), reqMethod);
            httpRequestFilter.afterHandle(httpRequest, response);
        } catch (Exception e) {
            log.warn("Handler method:[{}] exception:", reqMethod, e);
            httpRequestFilter.afterException(httpRequest, e);
        }

        return response;
    }

    private void handle(KunHttpRequest httpRequest, DefaultKunHttpResponse httpResponse, KunContext kunContext,
                        MethodInfo handlerMethod) {
        httpRequestHandlerChain.handle(httpRequest, httpResponse, kunContext, handlerMethod);
    }

}
