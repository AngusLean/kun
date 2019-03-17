package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.KunBootstrap;

/**
 * @author cupofish@gmail.com
 * 3/17/19 16:01
 */
public class KunMvcBootstrap {
    public static void main(String[] args) {
        KunBootstrap.start(KunMvcBootstrap.class);
    }
}
