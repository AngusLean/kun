package com.doubleysoft.kun;

import com.doubleysoft.kun.context.ClassInfo;
import com.doubleysoft.kun.scanner.ClassPathScannerImpl;
import com.doubleysoft.kun.scanner.Scanner;

import java.util.Set;

/**
 * Created by anguslean
 * 18-9-9 下午6:48
 */
public class KunContext {
    private String packages;
    private Scanner scanner;
    private Ioc ioc;

    public KunContext() {
        this(KunContext.class.getPackage().getName());
    }

    public KunContext(String packages) {
        this.scanner = new ClassPathScannerImpl();
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
