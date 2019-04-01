package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.mvc.KunMvcContext;
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
    private final KunContext kunContext;
    private static final ThreadLocal<KunMvcContext> CONTEXT_HOLDER = new ThreadLocal<>();

    @Override
    public KunHttpResponse handle(KunHttpRequest httpRequest) {
        KunHttpResponse response = new DefaultKunHttpResponse();
        createContext(httpRequest, response);
        MethodInfo reqMethod = MvcContextHolder.getRouter().getReqHandler(httpRequest.getReqURI());
        if (reqMethod == null) {
            log.error("illegle request path{}", httpRequest.getReqURI());
            throw new StateException("illeage request path");
        }
        Object result = reqMethod.execute(httpRequest.getContent());
        response.setContent(httpRequest.getContent());
        return response;
    }

    private KunMvcContext createContext(KunHttpRequest request, KunHttpResponse response) {
        KunMvcContext kunMvcContext = new KunMvcContext(request, response);
        CONTEXT_HOLDER.set(kunMvcContext);
        return kunMvcContext;
    }

}
