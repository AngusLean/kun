package com.doubleysoft.kun.mvc.mvccontext;

import com.doubleysoft.kun.mvc.KunMvcBootstrap;
import com.doubleysoft.kun.mvc.mvccontext.controller.IndexController;
import org.junit.Test;

/**
 * @author cupofish@gmail.com
 * 4/1/19 23:27
 */
public class KunMvcBootstrapTest {


    @Test
    public void reqPathTest() {
        KunMvcBootstrap.boot(IndexController.class);
    }


}