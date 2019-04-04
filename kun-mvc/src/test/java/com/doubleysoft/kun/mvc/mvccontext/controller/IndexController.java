package com.doubleysoft.kun.mvc.mvccontext.controller;

import javax.ws.rs.Path;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
@Path("/")
public class IndexController {
    @Path("/index")
    public String index() {
        return "hello world";
    }

    @Path("/user")
    public String user(String name) {
        return "hello " + name;
    }
}
