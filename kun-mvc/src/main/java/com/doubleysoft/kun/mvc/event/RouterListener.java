package com.doubleysoft.kun.mvc.event;

import com.doubleysoft.kun.ioc.context.ApplicationEventListener;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;

/**
 * @author cupofish@gmail.com
 * 3/23/19 21:44
 */
public class RouterListener implements ApplicationEventListener<ContextStartedEvent> {
    @Override
    public void onEvent(ContextStartedEvent event) {
        System.out.println(event);
    }
}
