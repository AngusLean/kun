package com.doubleysoft.kun.mvc.helper;

import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.util.AsmUtil;
import com.doubleysoft.kun.ioc.util.StrUtil;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;

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
        return getMethodParams(handlerMethod, request.getReqParams(), request.getContent());
    }

    /**
     * default simple-type params using name mapping
     *
     * @param methodInfo
     * @param reqParams
     * @return
     */
    private static Object[] getMethodParams(MethodInfo methodInfo, MultivaluedMap<String, Object> reqParams, String content) {
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

    private static Object getParameterValue(MethodInfo methodInfo, Parameter parameter, String parameterName,
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
        }
        if (isSpecialParam(parameter)) {
            return null;
        } else {
            return JsonUtil.map2Obj(contentMap, parameter.getType());
        }
    }

    private static boolean isSpecialParam(Parameter parameter) {
        return true;
    }
}
