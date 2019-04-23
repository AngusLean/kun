package com.doubleysoft.kun.mvc.helper;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author cupofish@gmail.com
 * 4/23/19 20:52
 */
@Slf4j
public class WebUtil {
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String decodeURIComponent(Object params) {
        if (params == null || params.toString().isEmpty()) {
            return null;
        }
        try {
            return URLDecoder.decode(params.toString(), DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            log.error("fail in decode string : {}", params);
            return params.toString();
        }
    }
}
