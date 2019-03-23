package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:15
 */
public class KunMvcContext {
    private KunHttpRequest request;
    private KunHttpResponse response;

    public KunMvcContext(KunHttpRequest request, KunHttpResponse response) {
        this.request = request;
        this.response = response;
    }
}
