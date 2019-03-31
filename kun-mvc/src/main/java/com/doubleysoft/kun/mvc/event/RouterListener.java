package com.doubleysoft.kun.mvc.event;

import com.doubleysoft.kun.ioc.context.AbstractApplicationContext;
import com.doubleysoft.kun.ioc.context.ApplicationEventListener;
import com.doubleysoft.kun.ioc.context.BeanDifination;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.MvcContants;
import com.doubleysoft.kun.mvc.server.Router;
import lombok.RequiredArgsConstructor;

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
        AbstractApplicationContext context = event.getApplicationContext();
        List<BeanDifination>       beans   = context.getBeans(MvcContants.getWebRequestBeanFilters());

    }
}
