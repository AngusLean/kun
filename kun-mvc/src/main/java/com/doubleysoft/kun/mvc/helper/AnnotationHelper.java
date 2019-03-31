package com.doubleysoft.kun.mvc.helper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/31/19 16:10
 */
public class AnnotationHelper {
    public static List<Class<? extends Annotation>> getWebReqAnno() {
        return Arrays.asList(Path.class, ApplicationPath.class);
    }
}
