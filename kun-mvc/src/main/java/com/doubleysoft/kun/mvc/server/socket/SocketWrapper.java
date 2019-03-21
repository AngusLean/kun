package com.doubleysoft.kun.mvc.server.socket;

import lombok.Getter;

import java.net.Socket;

/**
 * @author cupofish@gmail.com
 * 3/21/19 21:35
 */
public class SocketWrapper {
    @Getter
    private Socket socket;

    public SocketWrapper(Socket socket) {
        this.socket = socket;
    }
}
