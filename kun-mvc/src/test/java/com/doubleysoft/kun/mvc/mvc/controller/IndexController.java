package com.doubleysoft.kun.mvc.mvc.controller;

import javax.ws.rs.Path;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class IndexController {
    @Path("/index")
    public String index() {
        return "hello world";
    }
}
