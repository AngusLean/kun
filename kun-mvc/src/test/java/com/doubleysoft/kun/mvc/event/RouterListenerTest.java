package com.doubleysoft.kun.mvc.event;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.ApplicationEventListener;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.server.Router;
import org.junit.Test;

import javax.ws.rs.Path;

/**
 * @author cupofish@gmail.com
 * 3/31/19 17:05
 */
public class RouterListenerTest {
    @Test
    public void testWebReqEventListener() {
        Router     router  = new Router();
        KunContext context = new KunContext();
        context.addBean(RouterListenerTestKlass1.class);
        context.addBean(RouterListenerTestKlass2.class);
        ContextStartedEvent      contextStartedEvent = new ContextStartedEvent(context);
        ApplicationEventListener listener            = new RouterListener(router);
        listener.onEvent(contextStartedEvent);
        System.out.println(router);
    }


    private static class RouterListenerTestKlass1 {
        @Path(value = "/")
        public String index() {
            return "/";
        }

        @Path(value = "/name")
        public String name() {
            return "/name";
        }
    }

    private static class RouterListenerTestKlass2 {
        public String index() {
            return "/";
        }
    }
}