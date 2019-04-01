package com.doubleysoft.kun.mvc.event;

import com.doubleysoft.kun.ioc.context.ApplicationContext;
import com.doubleysoft.kun.ioc.context.ApplicationEventListener;
import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.MvcContants;
import com.doubleysoft.kun.mvc.server.MvcContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Path;
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
        ApplicationContext   context = event.getApplicationContext();
        List<BeanDifination> beans   = context.getBeans(MvcContants.getWebRequestBeanFilters());
        for (BeanDifination beanDifination : beans) {
            Class klass = beanDifination.getKlass();
            Arrays.stream(klass.getMethods())
                    .filter(row -> row.isAnnotationPresent(Path.class))
                    .forEach(row -> {
                        Path   annotation = row.getAnnotation(Path.class);
                        String value      = annotation.value();
                        MvcContextHolder.getRouter().addRoute(value, MethodInfo.builder()
                                .beanDifination(beanDifination).methodName(row.getName()).build());
                    });
        }
        log.info("all mvc mapping beans:{}", beans);
    }
}
