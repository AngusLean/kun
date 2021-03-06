package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.mvc.server.RequestHandler;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:06
 */
public interface Server {
    /**
     * start server in port, if port is negative, then use a random port
     *
     * @param port
     */
    void start(int port);


    /**
     * stop the server, this method will waiting for the service to stop
     */
    void stop();


    /**
     * stop now, ignore current processing service
     */
    void stopNow();

    /**
     * bing this socket to a process,sub class must call right process method
     *
     * @param requestHandler
     */
    void bindProcess(RequestHandler requestHandler);
}
