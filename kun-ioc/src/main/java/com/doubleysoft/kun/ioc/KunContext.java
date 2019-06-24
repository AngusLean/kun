package com.doubleysoft.kun.ioc;

import com.doubleysoft.kun.ioc.context.AbstractApplicationContext;
import com.doubleysoft.kun.ioc.context.ResourceInfo;
import com.doubleysoft.kun.ioc.context.event.bean.ContextStartedEvent;
import com.doubleysoft.kun.ioc.scanner.ClassInfoFilter;
import com.doubleysoft.kun.ioc.scanner.DefaultClassPathScannerImpl;
import com.doubleysoft.kun.ioc.scanner.Scanner;

import java.util.Iterator;
import java.util.Set;

/**
 * 18-9-9 下午6:48
 * @author anguslean
 */
public class KunContext extends AbstractApplicationContext {
    /**
     * package
     */
    private String packages;

    /**
     * class scanner
     */
    private Scanner scanner;

    public KunContext() {
        this(KunContext.class.getPackage().getName());
    }

    public KunContext(String packages) {
        this.scanner = new DefaultClassPathScannerImpl();
        this.packages = packages;
    }

    public void init() {
        Set<ResourceInfo> resourceInfos = scanner.scan(packages);
        Iterator<ResourceInfo> iterator = resourceInfos.iterator();
        while (iterator.hasNext()) {
            ResourceInfo resourceInfo = iterator.next();
            this.ioc.addBean(resourceInfo);
        }
        this.publishEvent(new ContextStartedEvent(this));
    }

    public void addClassInfoFilter(ClassInfoFilter classInfoFilter) {
        this.scanner.addClassInfoFilter(classInfoFilter);
    }


}
