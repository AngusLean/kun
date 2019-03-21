package com.doubleysoft.kun.mvc.server;

import com.doubleysoft.kun.ioc.exception.StateException;
import com.doubleysoft.kun.mvc.Server;
import com.doubleysoft.kun.mvc.server.protocal.RequestProcess;
import com.doubleysoft.kun.mvc.server.socket.SocketWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:09
 */
@Slf4j
public class BlockServer implements Server {
    private ServerSocket serverSocket;

    @Override
    public void start(int port) {
        log.debug("try to open socket in port:{}", port);
        try {
            serverSocket = new ServerSocket(port);
            log.info("server started in address:{}:{}", serverSocket.getInetAddress(), serverSocket.getLocalPort());
        } catch (Exception e) {
            log.error("error in init server socket", e);
            throw new StateException(e.getMessage());
        }
    }

    @Override
    public void stop() {
        log.info("begin to stop the server socket in local port:{}", serverSocket.getLocalPort());
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error("fail in close socket in port:{}", serverSocket.getLocalPort(), e);
        }
    }

    @Override
    public void stopNow() {

    }

    @Override
    public void bindProcess(RequestProcess requestProcess) {
        while (true) {
            try {
                Socket accept = serverSocket.accept();
                requestProcess.bindSocket(new SocketWrapper(accept));
            } catch (IOException e) {
                log.error("error in read data from socket", e);
            }
        }
    }
}
