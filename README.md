[![Build Status](https://travis-ci.org/AngusLean/kun.svg?branch=master)](https://travis-ci.org/AngusLean/kun)


## kun
A Simple IOC/MVC library, Which currently only support [JSR330](https://www.jcp.org/en/jsr/detail?id=330) Annotation


### Features
- Simple implementation :=
- Full support of JSR330
```aidl
    Inject
    Named
    Provider
    Qualifier
    Scope
    Singleton
```
- Simple MVC annotation:

```$xslt
JsonPath //Mark a JSON-TEXT response controller

MultipartParam //Mark a Multipart Param
```
By default, we mapping request parameter to handler method parameters
names in direct. For Basic Type, Just write right param names and anything 
is ok．

- SpringMVC like usage


### Example
```$xslt
//a single instance annotation
@Singleton
public class Demo3 {
}

//spring-like resource annotation
@Resource 
public class DefaultCps2 {
}

@Singleton
public class InjectUserController {
    //inject resource
    @Inject
    private UserDao userDao;

    public UserDO getUserInfo() {
        return userDao.getUserDo();
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

```

MVC:

```$xslt

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
}
@JsonPath
public class UserController {
    @Path("/add")
    public String index() {
        return "hello world";
    }

    @Delete("/del")
    public String delUser(@PathParam("name") String name) {
        return "hello " + name;
    }
}
```

### Usage
before you application start, call this method:
```$xslt
KunMvcApplication.start(Your-Class)  //clazz is the root package you wish to be scanned
```

