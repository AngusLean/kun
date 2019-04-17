package com.doubleysoft.kun.mvc;

import com.doubleysoft.kun.ioc.KunContext;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.mvc.event.RouterListener;
import com.doubleysoft.kun.mvc.filter.ioc.JaxRsClassInfoFilter;
import com.doubleysoft.kun.mvc.server.MvcContextHolder;
import com.doubleysoft.kun.mvc.server.Router;

/**
 * @author cupofish@gmail.com
 * 3/23/19 17:15
 */
public class KunMvcContext extends KunContext {

    public KunMvcContext(Class klass) {
        super(klass.getName().substring(0, klass.getName().lastIndexOf(".")));
        initMvcContext();
    }

    private void initMvcContext() {
        this.addClassInfoFilter(new JaxRsClassInfoFilter());
        this.addEventListener(ContextStartedEvent.class, new RouterListener());
        MvcContextHolder.init(this, new Router());
    }

}
