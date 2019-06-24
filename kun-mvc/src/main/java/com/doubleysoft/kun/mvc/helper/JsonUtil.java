package com.doubleysoft.kun.mvc.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.Map;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class JsonUtil {
    public static Map<String, Object> parse2Map(String content) {
        if (content == null || content.length() == 0) {
            return Collections.EMPTY_MAP;
        }
        return JSON.parseObject(content, Map.class);
//        return null;
    }

    public static <T> T parseObject(String content, Class<T> clazz) {
        return JSON.parseObject(content, clazz);
    }

    public static Object map2Obj(Map<String, Object> map, Class<?> clazz) {
        return new JSONObject(map).toJavaObject(clazz);
    }
}
