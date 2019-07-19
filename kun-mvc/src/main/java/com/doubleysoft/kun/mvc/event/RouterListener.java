package com.doubleysoft.kun.mvc.event;

import com.doubleysoft.kun.ioc.context.ApplicationContext;
import com.doubleysoft.kun.ioc.context.ApplicationEventListener;
import com.doubleysoft.kun.ioc.context.BeanDefinition;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.MvcContants;
import com.doubleysoft.kun.mvc.annotation.JsonPath;
import com.doubleysoft.kun.mvc.server.MvcContextHolder;
import com.doubleysoft.kun.mvc.server.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/23/19 21:44
 */
@RequiredArgsConstructor
@Slf4j
public class RouterListener implements ApplicationEventListener<ContextStartedEvent> {


    @Override
    public void onEvent(ContextStartedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        List<BeanDefinition> beans = context.getBeans(MvcContants.getWebRequestBeanFilters());

        beans.parallelStream().forEach(beanDefinition -> {
            Class clazz = beanDefinition.getClazz();
            String basePath = "";
            JsonPath jsonPath = (JsonPath) clazz.getAnnotation(JsonPath.class);
            if (jsonPath != null) {
                basePath = jsonPath.value();
            }
            final String tempBasePath = basePath;
            List<Method> methods = Arrays.asList(clazz.getMethods());
            scanHttpHandler(Path.class, beanDefinition, methods, tempBasePath, null);
            scanHttpHandler(GET.class, beanDefinition, methods, tempBasePath, HttpMethod.GET);
            scanHttpHandler(PUT.class, beanDefinition, methods, tempBasePath, HttpMethod.PUT);
            scanHttpHandler(POST.class, beanDefinition, methods, tempBasePath, HttpMethod.POST);
            scanHttpHandler(DELETE.class, beanDefinition, methods, tempBasePath, HttpMethod.DELETE);
            scanHttpHandler(HEAD.class, beanDefinition, methods, tempBasePath, HttpMethod.HEAD);
            scanHttpHandler(OPTIONS.class, beanDefinition, methods, tempBasePath, HttpMethod.OPTIONS);
        });
    }

    private <T extends Annotation> void scanHttpHandler(Class<T> tClass, BeanDefinition beanDefinition, List<Method> methods,
                                                        String tempBasePath, String httpMethod) {
        methods.stream().parallel()
                .filter(row -> row.isAnnotationPresent(tClass))
                .forEach(row -> {
                    T annotation = row.getAnnotation(tClass);
                    String relativePath = "";
                    try {
                        Method valueMethod = tClass.getDeclaredMethod("value");
                        if (valueMethod != null) {
                            relativePath = valueMethod.invoke(annotation).toString();
                        }
                    } catch (Exception e) {
                        log.warn("error in get property value of class [{}]", tClass, e);
                    }

                    String reqPath = tempBasePath + relativePath;
                    log.info("Mapping Request Path [{}] to Class:[{}], Method: [{}]", reqPath, row.getClass().getName(), row.getName());
                    MvcContextHolder.getRouter().addRoute(
                            Router.RouterKey.builder()
                                    .absPath(reqPath)
                                    .httpMethod(httpMethod)
                                    .build(),
                            MethodInfo.builder()
                                    .beanDefinition(beanDefinition)
                                    .methodName(row.getName())
                                    .build());
                });
    }


}
