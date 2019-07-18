package com.doubleysoft.kun.mvc.helper;

import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.util.AsmUtil;
import com.doubleysoft.kun.ioc.util.StrUtil;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;

import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

/**
 * @author cupofish@gmail.com
 * 3/31/19 16:10
 */
public class MvcHelper {
    public static Object[] getMethodCallParams(KunHttpRequest request, MethodInfo handlerMethod) {
        return getMethodParams(handlerMethod, request);
    }

    /**
     * default simple-type params using name mapping
     *
     * @param methodInfo
     * @param request
     * @return
     */
    private static Object[] getMethodParams(MethodInfo methodInfo, KunHttpRequest request) {
        MultivaluedMap<String, Object> reqParams = request.getReqParams();
        String content = request.getContent();
        if (reqParams.size() <= 0 && StrUtil.isNullOrEmpty(content)) {
            return new Object[0];
        }
        Method method = methodInfo.getMethod();
        Object[] methodParams = new Object[method.getParameterCount()];
        String[] methodParamNames = AsmUtil.getMethodParamNames(method);
        Map<String, Object> contentMap = JsonUtil.parse2Map(content);

        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < methodParamNames.length; i++) {
            Parameter parameter = parameters[i];

            //cookie
            CookieParam cookieParams = parameter.getAnnotation(CookieParam.class);
            if (cookieParams != null) {
                Cookie cookie = request.getCookie(cookieParams.value() == null ? methodParamNames[i] : cookieParams.value());
                if (cookie != null) {
                    methodParams[i] = parseCookie2Para(parameter, cookie);
                } else {
                    methodParams[i] = null;
                }
                continue;
            }

            HeaderParam headerParam = parameter.getAnnotation(HeaderParam.class);
            if (headerParam != null) {
                String headParams = request.getHeader(headerParam.value());
                if (headParams == null) {
                    methodParams[i] = null;
                } else {
                    methodParams[i] = parseObj(parameter, headParams);
                }
                continue;
            }

            QueryParam queryParam = parameter.getAnnotation(QueryParam.class);
            if (queryParam != null) {
                List<Object> queryParams = request.getReqParams().get(queryParam.value());
                if (queryParams == null || queryParams.size() == 0) {
                    methodParams[i] = null;
                } else {
                    methodParams[i] = parseObj(parameter, queryParams);
                }
            }

            //basic type
            if (MethodUtil.isBasicType(parameter.getType())) {
                methodParams[i] = getParameterValue(methodInfo, parameter, methodParamNames[i], reqParams, contentMap);
                continue;
            }

            methodParams[i] = JsonUtil.map2Obj(contentMap, parameters[i].getType());
        }
        return methodParams;
    }

    private static Object getParameterValue(MethodInfo methodInfo, Parameter parameter, String parameterName,
                                            MultivaluedMap<String, Object> reqParams, Map<String, Object> contentMap) {
        //for basic type, we mapping param value with name
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
    }

    /**
     * some special parameter , such as cookie, request, response ...etc.
     */
    private static Object parseCookie2Para(Parameter parameter, Cookie cookie) {
        return parseObj(parameter, cookie.getValue());
    }

    /**
     * some special parameter , such as cookie, request, response ...etc.
     */
    private static Object parseHeader2Para(Parameter parameter, List<Object> headParams) {
        return parseObj(parameter, headParams.get(0));
    }

    private static Object parseObj(Parameter parameter, Object value) {
        return MethodUtil.extractParam(parameter, value);
    }
}
