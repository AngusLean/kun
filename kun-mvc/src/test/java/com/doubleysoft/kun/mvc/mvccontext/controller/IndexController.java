package com.doubleysoft.kun.mvc.mvccontext.controller;

import javax.ws.rs.CookieParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
@Path("/")
public class IndexController {
    @Path("/index")
    public String index() {
        return "hello world";
    }

    @Path("/user")
    public String user(@PathParam("name") String name) {
        return "hello " + name;
    }

    @Path("/cookie")
    public String cookie(@CookieParam("user_name") String userName) {
        return "[cookie] hello: " + userName;
    }
}
