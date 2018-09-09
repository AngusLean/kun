package com.doubleysoft.kun;

/**
 * Created by anguslean
 * 18-9-9 下午6:04
 */
public class KunBootstrap {
    public static void start(Class klass) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                KunContext kunContext = new KunContext(klass.getPackage().getName());
                kunContext.init();
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
