package com.doubleysoft.kun.mvc.server.model;

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
    String getContent();

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
    String getReqURI();

    String getReqURL();
}