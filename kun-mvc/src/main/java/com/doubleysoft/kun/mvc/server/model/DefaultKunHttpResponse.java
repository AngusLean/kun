package com.doubleysoft.kun.mvc.server.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.doubleysoft.kun.mvc.server.Const.CONTENT_TYPE;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:55
 */
public class DefaultKunHttpResponse implements KunHttpResponse {
    private String content;
    private int status;
    private Map<String, String> headers;

    public DefaultKunHttpResponse(){
        headers = new HashMap<>();
    }


    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContentType() {
        return this.headers.get(CONTENT_TYPE);
    }

    @Override
    public void setContentType(String contentType) {
        this.headers.put(CONTENT_TYPE, contentType);
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    @Override
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
