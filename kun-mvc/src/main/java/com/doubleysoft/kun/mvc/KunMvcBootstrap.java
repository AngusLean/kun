package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.event.RouterListener;
import com.doubleysoft.kun.mvc.filter.ioc.JaxRsClassInfoFilter;
import com.doubleysoft.kun.mvc.server.DefaultRequestHandler;
import com.doubleysoft.kun.mvc.server.MvcContextHolder;
import com.doubleysoft.kun.mvc.server.netty.NettyServer;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:01
 */
public class KunMvcBootstrap {
    public static void main(String[] args) {
        boot(KunMvcBootstrap.class);
    }

    public static void boot(Class klass) {
        KunContext kunContext = startContext(klass);
        startServer(kunContext);
    }

    private static void startServer(KunContext kunContext) {
        Server                server         = new NettyServer();
        DefaultRequestHandler requestHandler = new DefaultRequestHandler(kunContext);
        server.bindProcess(requestHandler);
        server.start(8080);

        server.stop();
    }

    private static KunContext startContext(Class klass) {
        KunContext kunContext = new KunContext(klass.getPackage().getName());
        MvcContextHolder.init(kunContext);
        kunContext.addEventListener(ContextStartedEvent.class, new RouterListener());
        kunContext.addClassInfoFilter(new JaxRsClassInfoFilter());
        kunContext.init();
        return kunContext;
    }

}
