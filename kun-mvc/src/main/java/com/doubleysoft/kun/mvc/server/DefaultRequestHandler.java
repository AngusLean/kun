package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.mvc.KunMvcContext;
import com.doubleysoft.kun.mvc.server.model.DefaultKunHttpResponse;
import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:17
 */
public class DefaultRequestHandler implements RequestHandler {
    private static final ThreadLocal<KunMvcContext> CONTEXT_HOLDER = new ThreadLocal<>();

    @Override
    public KunHttpResponse handle(KunHttpRequest httpRequest) {
        KunHttpResponse response = new DefaultKunHttpResponse();
        createContext(httpRequest, response);

        response.setContent(httpRequest.getContent());
        return response;
    }

    private KunMvcContext createContext(KunHttpRequest request, KunHttpResponse response) {
        KunMvcContext kunMvcContext = new KunMvcContext(request, response);
        CONTEXT_HOLDER.set(kunMvcContext);
        return kunMvcContext;
    }

}
