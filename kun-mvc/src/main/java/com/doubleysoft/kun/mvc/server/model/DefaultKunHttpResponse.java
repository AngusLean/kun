package com.doubleysoft.kun.mvc.server.model;

import lombok.Setter;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:55
 */
public class DefaultKunHttpResponse implements KunHttpResponse {
    @Setter
    private String content;

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
