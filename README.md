[![Build Status](https://travis-ci.org/AngusLean/kun.svg?branch=master)](https://travis-ci.org/AngusLean/kun)


## kun
A Simple Inject Library, Which currently only support [JSR330](https://www.jcp.org/en/jsr/detail?id=330) Annotation


### Features
- simple implementation:=
- full support of JSR330
```aidl
    Inject
    Named
    Provider
    Qualifier
    Scope
    Singleton
```

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

### Usage
before you application start, call this method:
```$xslt
KunBootstrap.start(Class klass)  //klass is the root package you wish to be scanned
```