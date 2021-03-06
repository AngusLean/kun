package com.doubleysoft.kun.mvc.server.model;

import com.doubleysoft.kun.mvc.http.ContentTypeEnum;

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
     * Get current response headers
     *
     * @return return response headers
     */
    Map<String, String> getHeaders();

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

    /**
     * get the response body content stream
     *
     * @return
     */
    void setContent(Object content);

    /**
     * get http request cont-type
     *
     * @return
     */
    ContentTypeEnum contentType();
}
