package com.doubleysoft.kun;

import com.doubleysoft.kun.context.ClassInfo;
import com.doubleysoft.kun.context.event.ApplicationEventRegister;
import com.doubleysoft.kun.scanner.DefaultClassPathScannerImpl;
import com.doubleysoft.kun.scanner.Scanner;

import java.util.Set;

/**
 * Created by anguslean
 * 18-9-9 下午6:48
 */
public class KunContext {
    private String packages;

    /**
     * class scanner
     */
    private Scanner scanner;

    /**
     * ioc container
     */
    private Ioc ioc;

    /**
     * event register
     */
    private ApplicationEventRegister eventRegister;

    public KunContext() {
        this(KunContext.class.getPackage().getName());
    }

    public KunContext(String packages) {
        this.scanner = new DefaultClassPathScannerImpl();
        this.ioc = new KunIoc();
        this.packages = packages;
    }

    public void init() {
        Set<ClassInfo> classInfos = scanner.scan(packages);
        while (classInfos.iterator().hasNext()) {
            ClassInfo classInfo = classInfos.iterator().next();
            ioc.addBean(classInfo.getKlass());
        }
    }
}
