package com.doubleysoft.kun.mvc.helper;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
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

    public static MultivaluedMap<String, Object> unwrapURIParams(String uri) {
        MultivaluedMap<String, Object> result = new MultivaluedHashMap<>();
        int queryChartPos = uri.lastIndexOf("?");
        if (queryChartPos != -1) {
            String queryParam = uri.substring(queryChartPos + 1, uri.length());
            for (String param : queryParam.split("&")) {
                String[] keyValue = param.split("=");
                if (keyValue.length != 2) {
                    log.warn("request param:{} is illegal ", keyValue);
                } else {
                    result.add(keyValue[0], keyValue[1]);
                }
            }
        }
        return result;
    }
}
