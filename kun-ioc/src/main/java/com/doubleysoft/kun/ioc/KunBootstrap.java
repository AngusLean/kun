package com.doubleysoft.kun.ioc;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by anguslean
 * 18-9-9 下午6:04
 */
@Slf4j
public class KunBootstrap {
    public static void start(Class clazz) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                KunContext kunContext = new KunContext(clazz.getPackage().getName());
                kunContext.init();
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            log.error("fail in start kun ioc", e);
        }
    }
}
