package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.mvc.server.model.KunHttpRequest;
import com.doubleysoft.kun.mvc.server.model.KunHttpResponse;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:12
 */
public interface RequestHandler {
    KunHttpResponse handle(KunHttpRequest httpRequest);
}
