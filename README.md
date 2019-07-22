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
    public String delUser(String name) {
        return "hello " + name;
    }
    @Get("/content")
    public void content(Float age, @CookieParam(value = "token") String token) {
    
    }
    @Get("/addUser")
    public void content(int age, @QueryParam(value = "name") String name) {
    
    }
    @Get("/setHeader")
    public void content(int age, @HeaderParam(value = "names") List<String> names) {
    
    }
    /**
    * if only one parameter in method and request content
    * is not empty, this parameter will automatic mapped.
    */
    @Post("/user")
    public void addUser(User user){
    }
}
```

**Note**
>>>
Controller method parameter name is automatic mapped by name．and use follow strategy:
1. use URI params, for example:
```apple js
http://www.baidu.com?name=zhangsna&age=22
```
will generate "name" and "age" parameters．if parameter is't match,then:

2. deserialize POST body as a key-value map, and find params.
```apple js
bash:  curl -X '{age:33}' http://www.baidu.com
```
will generate 'age' parameter.

### Usage
before you application start, call this method:
```$xslt
KunMvcApplication.start(Your-Class)  //clazz is the root package you wish to be scanned
```

