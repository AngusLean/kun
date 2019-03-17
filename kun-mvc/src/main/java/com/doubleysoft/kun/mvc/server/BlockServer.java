package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.mvc.Server;
import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:09
 */
@Slf4j
public class BlockServer implements Server {
    private ServerSocket serverSocket;

    @Override
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            log.error("error in init server socket", e);
            throw new StateException(e.getMessage());
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void stopNow() {

    }
}
