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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URLDecoder;
import java.util.List;

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
        Method      method           = handlerMethod.getMethod();
        Parameter[] methodParameters = method.getParameters();
        Object[]    callParam        = new Object[methodParameters.length];

        if (method.getParameterCount() != 0) {
            MultivaluedMap<String, Object> reqParams = httpRequest.getReqParams();

            if (reqParams.size() != 0) {
                setHandlerMethodParams(callParam, methodParameters, reqParams);
            }
        }

        Object response = handlerMethod.execute(callParam);
        httpResponse.setContent(response == null ? null : response.toString());
    }

    private void setHandlerMethodParams(Object[] methodParams, Parameter[] methodParameters, MultivaluedMap<String, Object> reqParams) {
        int index = 0;
        for (Parameter parameter : methodParameters) {
            if (parameter.getAnnotationsByType(PathParam.class) == null) {
                continue;
            }
            List<Object> objects = reqParams.get(parameter.getAnnotation(PathParam.class).value());
            if (objects == null || objects.size() <= 0) {
                continue;
            }
            String param;
            try {
                param = URLDecoder.decode(objects.get(0).toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("fail in decode string : {}", objects.get(0).toString());
                param = objects.get(0).toString();
            }
            methodParams[index] = param;
        }
        index++;
    }
}
