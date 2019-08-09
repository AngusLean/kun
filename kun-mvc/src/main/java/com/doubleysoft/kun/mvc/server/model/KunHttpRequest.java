package com.doubleysoft.kun.mvc.server.model;

import com.doubleysoft.kun.mvc.http.ContentTypeEnum;
import com.doubleysoft.kun.mvc.http.HttpMethodEnum;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author cupofish@gmail.com
 * 3/23/19 16:03
 */
public interface KunHttpRequest {
    /**
     * append UTF-8 content
     *
     * @param content
     */
    void appendContent(String content);

    /**
     * get the whole request content
     *
     * @return
     */
    String content();

    /**
     * the request is keep alive
     *
     * @return
     */
    boolean isKeepAlive();

    /**
     * get request url, for example ,"www.baidu.com/ads".
     * uri is "/ads"
     *
     * @return
     */
    String reqURI();

    String getReqURL();

    /**
     * Get all request params, include url-param in Get request and Form param in Post Request
     *
     * @return
     */
    MultivaluedMap<String, Object> reqParams();

    Cookie cookie(String key);

    /**
     * get header with specified key
     *
     * @param key
     * @return
     */
    String header(String key);

    /**
     * get http request method
     *
     * @return
     */
    HttpMethodEnum method();

    /**
     * get http request cont-type
     *
     * @return
     */
    ContentTypeEnum contentType();
}
