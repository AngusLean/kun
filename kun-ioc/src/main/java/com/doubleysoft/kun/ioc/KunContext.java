package com.doubleysoft.kun.ioc;

import com.doubleysoft.kun.ioc.context.AbstractApplicationContext;
import com.doubleysoft.kun.ioc.context.ClassInfo;
import com.doubleysoft.kun.ioc.context.event.bean.BeanAfterConstructEvent;
import com.doubleysoft.kun.ioc.context.event.bean.BeanBeforeConstructEvent;
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
        super(new KunIoc());
        this.scanner = new DefaultClassPathScannerImpl();
        this.packages = packages;
    }

    public void init() {
        Set<ClassInfo>      classInfos = scanner.scan(packages);
        Iterator<ClassInfo> iterator   = classInfos.iterator();
        while (iterator.hasNext()) {
            ClassInfo classInfo = iterator.next();
            this.ioc.addBean(classInfo);
            doCreateBean(classInfo);
        }
        this.publishEvent(new ContextStartedEvent(this));
    }

    public void addClassInfoFilter(ClassInfoFilter classInfoFilter) {
        this.scanner.addClassInfoFilter(classInfoFilter);
    }

    private void doCreateBean(ClassInfo classInfo) {
        if (KunConfig.isCreateBeanOnInit() && !classInfo.isLazyInit()) {
            this.publishEvent(new BeanBeforeConstructEvent(classInfo.getKlass().getSimpleName(), this));
            this.getBean(classInfo.getKlass());
            this.publishEvent(new BeanAfterConstructEvent(classInfo.getKlass().getSimpleName(), this));
        }
    }


}
