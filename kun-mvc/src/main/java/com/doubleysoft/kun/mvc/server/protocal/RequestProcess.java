package com.doubleysoft.kun.mvc.server.protocal;

import com.doubleysoft.kun.mvc.server.socket.SocketWrapper;

/**
 * @author cupofish@gmail.com
 * 3/21/19 21:42
 */

public class RequestProcess {

    public void bindSocket(SocketWrapper socket) {
        new HttpProcessor().process(socket);
    }
}
