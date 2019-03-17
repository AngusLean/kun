package com.doubleysoft.kun.mvc;

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
}
