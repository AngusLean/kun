package com.doubleysoft.kun;

import com.doubleysoft.kun.context.AbstractApplicationContext;
import com.doubleysoft.kun.context.ClassInfo;
import com.doubleysoft.kun.context.event.ApplicationEventRegister;
import com.doubleysoft.kun.scanner.DefaultClassPathScannerImpl;
import com.doubleysoft.kun.scanner.Scanner;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by anguslean
 * 18-9-9 下午6:48
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

    /**
     * event register
     */
    private ApplicationEventRegister eventRegister;

    public KunContext() {
        this(KunContext.class.getPackage().getName());
    }

    public KunContext(String packages) {
        super(new KunIoc());
        this.scanner = new DefaultClassPathScannerImpl();
        this.packages = packages;
    }

    public void init() {
        Set<ClassInfo> classInfos = scanner.scan(packages);
        Iterator<ClassInfo> iterator = classInfos.iterator();
        while (iterator.hasNext()) {
            ClassInfo classInfo = iterator.next();
            this.addBean(classInfo.getKlass());
        }
    }
}
