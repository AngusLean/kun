package com.doubleysoft.kun.mvc.helper;

import com.doubleysoft.kun.mvc.annotation.JsonPath;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 16:10
 */
public class MvcAnnotationHelper {
    public static List<Class<? extends Annotation>> getWebReqAnno() {
        return Arrays.asList(Path.class, ApplicationPath.class, JsonPath.class);
    }
}
