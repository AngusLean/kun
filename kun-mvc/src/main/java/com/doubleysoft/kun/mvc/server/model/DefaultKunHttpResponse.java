package com.doubleysoft.kun.mvc.server.model;

import lombok.Getter;
import lombok.Setter;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:55
 */
public class DefaultKunHttpResponse implements KunHttpResponse {
    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private int status;

    @Getter
    private Map<String, String> headers;

    public DefaultKunHttpResponse(){
        headers = new HashMap<>();
    }

    @Override
    public OutputStream getResponseBody() {
        return null;
    }
}
