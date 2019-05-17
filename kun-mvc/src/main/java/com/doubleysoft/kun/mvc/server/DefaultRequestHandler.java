package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.ioc.util.AsmUtil;
import com.doubleysoft.kun.mvc.KunMvcContext;
import com.doubleysoft.kun.mvc.helper.MethodUtil;
import com.doubleysoft.kun.mvc.helper.WebUtil;
import com.doubleysoft.kun.mvc.server.model.DefaultKunHttpResponse;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        handle(httpRequest, response, MvcContextHolder.getKunContext(), reqMethod);
        return response;
    }

    private KunMvcContext createMvcContext(KunHttpRequest request, KunHttpResponse response) {
        KunContext kunContext = MvcContextHolder.getKunContext();
//        return new KunMvcContext()
        return null;

    }

    private void handle(KunHttpRequest httpRequest, KunHttpResponse httpResponse, KunContext kunContext,
                        MethodInfo handlerMethod) {
        Object[] callParam = getMethodParams(handlerMethod, httpRequest.getReqParams());
        Object   response  = handlerMethod.execute(kunContext.getBean(handlerMethod.getBeanName()), callParam);
        httpResponse.setContent(response == null ? null : response.toString());
    }

    private Object[] getMethodParams(MethodInfo methodInfo, MultivaluedMap<String, Object> reqParams) {
        Method   method       = methodInfo.getMethod();
        Object[] methodParams = new Object[method.getParameterCount()];
        if (reqParams.size() > 0) {
            String[]    methodParamNames = AsmUtil.getMethodParamNames(method);
            Parameter[] parameters       = method.getParameters();
            for (int i = 0; i < methodParamNames.length; i++) {
                if (reqParams.containsKey(methodParamNames[i])) {
                    List<Object> reqParamValues = reqParams.get(methodParamNames[i]);
                    if (reqParamValues.size() == 0) {
                        continue;
                    }
                    if (parameters[i].isVarArgs()) {
                        methodParams[i] = reqParamValues.toArray();
                    } else {
                        Object value = reqParamValues.get(0);
                        value = MethodUtil.extractParam(parameters[i], value);
                        if (value instanceof String) {
                            value = methodInfo.isDecodeReqParam() ? WebUtil.decodeURIComponent(value) : value;
                        }
                        methodParams[i] = value;
                    }
                }
            }
        }
        return methodParams;
    }

}
