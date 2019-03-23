package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.event.RouterListener;
import com.doubleysoft.kun.mvc.server.netty.NettyServer;
import com.doubleysoft.kun.mvc.server.protocal.RequestProcess;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:01
 */
public class KunMvcBootstrap {
    public static void main(String[] args) {
//        KunBootstrap.start(KunMvcBootstrap.class);
        startContext();
        startServer();
    }

    private static void startServer() {
        Server         server         = new NettyServer();
        RequestProcess requestProcess = new RequestProcess();
        server.start(8080);
        server.bindProcess(requestProcess);
        server.stop();
    }

    private static void startContext() {
        KunContext kunContext = new KunContext(KunMvcBootstrap.class.getPackage().getName());
        kunContext.registerEventListener(ContextStartedEvent.class, new RouterListener());
        kunContext.init();

    }
}
