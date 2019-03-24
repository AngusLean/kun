package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.event.RouterListener;
import com.doubleysoft.kun.mvc.filter.ioc.JaxRsClassInfoFilter;
import com.doubleysoft.kun.mvc.server.netty.NettyServer;
import com.doubleysoft.kun.mvc.server.protocal.RequestProcess;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:01
 */
public class KunMvcBootstrap {
    public static void main(String[] args) {
        boot(KunMvcBootstrap.class);
    }

    public static void boot(Class klass) {
        startContext(klass);
        startServer();
    }

    private static void startServer() {
        Server         server         = new NettyServer();
        RequestProcess requestProcess = new RequestProcess();
        server.start(8080);
        server.bindProcess(requestProcess);
        server.stop();
    }

    private static void startContext(Class klass) {
        KunContext kunContext = new KunContext(klass.getPackage().getName());
        kunContext.addEventListener(ContextStartedEvent.class, new RouterListener());
        kunContext.addClassInfoFilter(new JaxRsClassInfoFilter());
        kunContext.init();
    }

}
