package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.util.AsmUtil;
import com.doubleysoft.kun.ioc.util.StrUtil;
import com.doubleysoft.kun.mvc.helper.MethodUtil;
import com.doubleysoft.kun.mvc.helper.WebUtil;
import com.doubleysoft.kun.mvc.server.model.DefaultKunHttpResponse;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import com.doubleysoft.kun.mvc.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

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
        Object[] callParam = getMethodParams(handlerMethod, httpRequest.getReqParams(), httpRequest.getContent());

        //response content
        Object response = handlerMethod.execute(kunContext.getBean(handlerMethod.getBeanName()), callParam);
        httpResponse.setContent(response == null ? null : response.toString());

        //response headers
        setResponseHeaders(httpRequest, httpResponse, handlerMethod);
    }

    private void setResponseHeaders(KunHttpRequest httpRequest, KunHttpResponse httpResponse, MethodInfo handlerMethod) {
        httpResponse.setStatus(200);
    }


    /**
     * default simple-type params using name mapping
     *
     * @param methodInfo
     * @param reqParams
     * @return
     */
    private Object[] getMethodParams(MethodInfo methodInfo, MultivaluedMap<String, Object> reqParams, String content) {
        if (reqParams.size() <= 0 && StrUtil.isNullOrEmpty(content)) {
            return new Object[0];
        }
        Method method = methodInfo.getMethod();
        Object[] methodParams = new Object[method.getParameterCount()];
        String[] methodParamNames = AsmUtil.getMethodParamNames(method);
        Map<String, Object> contentMap = JsonUtil.parse2Map(content);

        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < methodParamNames.length; i++) {
            methodParams[i] = getParameterValue(methodInfo, parameters[i], methodParamNames[i], reqParams, contentMap);
        }
        return methodParams;
    }

    private Object getParameterValue(MethodInfo methodInfo, Parameter parameter, String parameterName,
                                     MultivaluedMap<String, Object> reqParams, Map<String, Object> contentMap) {
        //for basic type, we mapping param value with name
        if (MethodUtil.isBasicType(parameter.getType())) {
            List<Object> reqParamValues = reqParams.get(parameterName);
            Object value;
            if (reqParamValues == null) {
                if (!contentMap.containsKey(parameterName)) {
                    return null;
                }
                //parameter from http request content
                value = contentMap.get(parameterName);
            } else if (parameter.isVarArgs()) {
                return reqParamValues.toArray();
            } else {
                //parameter from http request uri
                value = reqParamValues.get(0);
            }

            value = MethodUtil.extractParam(parameter, value);
            if (value instanceof String) {
                return methodInfo.isDecodeReqParam() ? WebUtil.decodeURIComponent(value) : value;
            }
            return value;
        } else {
            return JsonUtil.map2Obj(contentMap, parameter.getType());
        }
    }

}
