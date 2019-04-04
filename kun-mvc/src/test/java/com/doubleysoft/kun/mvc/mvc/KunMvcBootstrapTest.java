package com.doubleysoft.kun.mvc.mvc;

import com.doubleysoft.kun.mvc.KunMvcBootstrap;
import com.doubleysoft.kun.mvc.mvc.controller.IndexController;
import org.junit.Ignore;

/**
 * @author cupofish@gmail.com
 * 4/1/19 23:27
 */
public class KunMvcBootstrapTest {

    @Ignore
    public void boot() {
        KunMvcBootstrap.boot(IndexController.class);
        System.out.println("d123123");
    }


}