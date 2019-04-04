package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.mvc.server.DefaultRequestHandler;
import com.doubleysoft.kun.mvc.server.netty.NettyServer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:01
 */
@Slf4j
public class KunMvcBootstrap {
    private static Server server;

    public static void main(String[] args) {
        boot(KunMvcBootstrap.class);
    }

    public static void boot(Class klass) {
        startContext(klass);
        startServer();
        log.info("start server");
    }

    public static void shutdown() {
        if (server != null) {
            server.stop();
            log.info("close server");
        }
    }

    private static void startServer() {
        server = new NettyServer();
        DefaultRequestHandler requestHandler = new DefaultRequestHandler();
        server.bindProcess(requestHandler);
        server.start(8080);
    }

    private static KunContext startContext(Class klass) {
        KunMvcContext kunContext = new KunMvcContext(klass);
        kunContext.init();
        return kunContext;
    }

}
