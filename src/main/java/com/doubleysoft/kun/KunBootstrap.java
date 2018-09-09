package com.doubleysoft.kun;

/**
 * Created by anguslean
 * 18-9-9 下午6:04
 */
public class KunBootstrap {
    public static void start() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

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
