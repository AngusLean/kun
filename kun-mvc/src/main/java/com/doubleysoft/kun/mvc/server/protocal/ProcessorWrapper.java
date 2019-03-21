package com.doubleysoft.kun.mvc.server.protocal;

import com.doubleysoft.kun.mvc.server.socket.SocketWrapper;
import lombok.AllArgsConstructor;

/**
 * @author cupofish@gmail.com
 * 3/21/19 21:34
 */
@AllArgsConstructor
public class ProcessorWrapper {
    private final ReqProcessor reqProcessor;

    public void processSocket(SocketWrapper socket) {
        reqProcessor.process(socket);
    }
}
