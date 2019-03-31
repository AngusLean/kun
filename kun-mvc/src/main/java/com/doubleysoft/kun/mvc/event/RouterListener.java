package com.doubleysoft.kun.mvc.event;

import com.doubleysoft.kun.ioc.context.ApplicationContext;
import com.doubleysoft.kun.ioc.context.ApplicationEventListener;
import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.context.MethodInfo;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.MvcContants;
import com.doubleysoft.kun.mvc.server.Router;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.List;

/**
 * @author cupofish@gmail.com
 * 3/23/19 21:44
 */
@RequiredArgsConstructor
public class RouterListener implements ApplicationEventListener<ContextStartedEvent> {
    private final Router router;

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
                        router.addRoute(value, MethodInfo.builder()
                                .beanDifination(beanDifination).methodName(row.getName()).build());
                    });
        }
        System.out.println(beans);
    }
}
