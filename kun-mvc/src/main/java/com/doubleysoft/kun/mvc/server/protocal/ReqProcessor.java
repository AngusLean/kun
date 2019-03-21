package com.doubleysoft.kun.mvc.server.protocal;

import com.doubleysoft.kun.mvc.server.socket.SocketWrapper;

/**
 * @author cupofish@gmail.com
 * 3/21/19 21:36
 */
public interface ReqProcessor {
    void process(SocketWrapper socketWrapper);
}
