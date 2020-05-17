package com.doubleysoft.kun.demo.controller;

import com.doubleysoft.kun.mvc.annotation.JsonPath;

import javax.ws.rs.CookieParam;
import javax.ws.rs.Path;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
@JsonPath("/")
public class UserController {

    @Path("index")
    public String index() {
        return "Hello world";
    }

    @Path("user")
    public String addUser(String name, Integer age) {
        return "Hello " + name + ", age is " + age;
    }

    @Path("cookie")
    public String getCookie(@CookieParam(value = "auth") String auth) {
        return auth;
    }
}
