package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.KunBootstrap;
import com.doubleysoft.kun.mvc.server.netty.NettyServer;
import com.doubleysoft.kun.mvc.server.protocal.RequestProcess;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:01
 */
public class KunMvcBootstrap {
    public static void main(String[] args) {
        KunBootstrap.start(KunMvcBootstrap.class);
        startServer();
    }

    private static void startServer() {
        Server         server         = new NettyServer();
        RequestProcess requestProcess = new RequestProcess();
        server.start(8080);
        server.bindProcess(requestProcess);
        server.stop();
    }
}
