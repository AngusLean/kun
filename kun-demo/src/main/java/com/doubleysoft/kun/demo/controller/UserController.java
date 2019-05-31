package com.doubleysoft.kun.demo.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
@Path("/")
public class UserController {

    @Path("index")
    public String index() {
        return "Hellow world";
    }

    @Path("user")
    public String addUser(String name, Inject age) {
        return "Hellow " + name + ", age is " + age;
    }
}
