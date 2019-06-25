package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.mvc.helper.DateUtil;
import com.doubleysoft.kun.mvc.helper.MvcHelper;
import com.doubleysoft.kun.mvc.server.model.DefaultKunHttpResponse;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.doubleysoft.kun.mvc.server.Const.KEY_CONTENT_TYPE;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:17
 */
@RequiredArgsConstructor
@Slf4j
public class DefaultRequestHandler implements RequestHandler {

    @Override
    public KunHttpResponse handle(KunHttpRequest httpRequest) {
        KunHttpResponse response = new DefaultKunHttpResponse();
        MethodInfo reqMethod = MvcContextHolder.getRouter().getReqHandler(httpRequest.getReqURI());
        if (reqMethod == null) {
            log.error("Not found request path [{}] mapping", httpRequest.getReqURI());
            response.setStatus(404);
            return response;
        }
        handle(httpRequest, response, MvcContextHolder.getKunContext(), reqMethod);
        return response;
    }

    private void handle(KunHttpRequest httpRequest, KunHttpResponse httpResponse, KunContext kunContext,
                        MethodInfo handlerMethod) {
        Object[] callParam = MvcHelper.getMethodCallParams(httpRequest, handlerMethod);

        //response content
        Object response = handlerMethod.execute(kunContext.getBean(handlerMethod.getBeanName()), callParam);
        httpResponse.setContent(response == null ? null : response.toString());

        //response headers
        setResponseHeaders(httpRequest, httpResponse, handlerMethod);
    }

    private void setResponseHeaders(KunHttpRequest httpRequest, KunHttpResponse httpResponse, MethodInfo handlerMethod) {
        httpResponse.setStatus(200);
        httpResponse.getHeaders().put("Date", DateUtil.gmtDate());
        httpResponse.getHeaders().put("Last-Modified", DateUtil.gmtDate());
        httpResponse.getHeaders().put(KEY_CONTENT_TYPE, "application/json;charset=utf-8");
        if (httpRequest.isKeepAlive()) {
            httpResponse.getHeaders().put("Connection", "keep-alive");
        }
    }

}
