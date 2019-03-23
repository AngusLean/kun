package com.doubleysoft.kun.mvc.server.model;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:11
 */
public interface KunHttpResponse {
    /**
     * get the whole request content
     *
     * @return
     */
    String getContent();

    /**
     * set response content
     */
    void setContent(String content);
}
