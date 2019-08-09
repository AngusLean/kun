package com.doubleysoft.kun.mvc.http;

/**
 * @author cupofish@gmail.com
 * 8/9/19 22:17
 */
public enum ContentTypeEnum {
    APPLICATION_JSON("application/json");


    private String contentType;

    ContentTypeEnum(String _contentType) {
        this.contentType = _contentType;
    }
}
