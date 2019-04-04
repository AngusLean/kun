package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.mvc.server.model.DefaultKunHttpResponse;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:17
 */
@RequiredArgsConstructor
@Slf4j
public class DefaultRequestHandler implements RequestHandler {

    @Override
    public KunHttpResponse handle(KunHttpRequest httpRequest) {
        KunHttpResponse response  = new DefaultKunHttpResponse();
        MethodInfo      reqMethod = MvcContextHolder.getRouter().getReqHandler(httpRequest.getReqURI());
        if (reqMethod == null) {
            log.error("Illegal request path{}", httpRequest.getReqURI());
            throw new StateException("Illegal request path");
        }
        handle(httpRequest, response, reqMethod);
        return response;
    }


    private void handle(KunHttpRequest httpRequest, KunHttpResponse httpResponse, MethodInfo handlerMethod) {
        Method      method     = handlerMethod.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[]    reqParam   = new Object[parameters.length];

        if (method.getParameterCount() != 0) {
            MultivaluedMap<String, Object> reqParams = httpRequest.getReqParams();
            int                            index     = 0;
            if (reqParams.size() != 0) {
                for (Parameter parameter : parameters) {
                    if (parameter.getAnnotationsByType(PathParam.class) != null) {

                    }
                    if (reqParams.containsKey(parameter.getName())) {
                        reqParam[index] = reqParams.get(parameter.getName());
                    }
                    index++;
                }
            }
        }

        Object response = handlerMethod.execute(reqParam);
        httpResponse.setContent(response == null ? null : response.toString());
    }
}
