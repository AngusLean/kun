package com.doubleysoft.kun.mvc.server.model;

import java.util.Map;

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

    /**
     * get Content-Type
     * @return
     */
    String getContentType();

    /**
     * set Content-Type, default is application/json;charset=utf-8
     * @param contentType
     */
    void setContentType(String contentType);

    /**
     * Get current response headers
     *
     * @return return response headers
     */
    Map<String, String> getHeaders();
    String getHeader(String name);

    /**
     * Set current response header
     *
     * @param name  Header Name
     * @param value Header Value
     * @return Return Response
     */
    void setHeader(String name, String value);

    /**
     * set status
     * @param status
     */
    void setStatus(int status);

    /**
     * get status
     * @return
     */
    int getStatus();
}
