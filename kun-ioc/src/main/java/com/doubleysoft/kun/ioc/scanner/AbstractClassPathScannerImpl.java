package com.doubleysoft.kun.ioc.scanner;

import com.doubleysoft.kun.ioc.context.ClassInfo;
import com.doubleysoft.kun.ioc.exception.StateException;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by anguslean
 * 18-9-9 下午2:33
 */
@Slf4j
public abstract class AbstractClassPathScannerImpl implements Scanner {

    @Override
    public Set<ClassInfo> scan(String packages) {
        Set<ClassInfo> lists        = new HashSet<>();
        String         packagesPath = packages.replace(".", "/");
        try {
            Enumeration<URL> urls = AbstractClassPathScannerImpl.class.getClassLoader().getResources(packagesPath);
            while (urls.hasMoreElements()) {
                URL    url  = urls.nextElement();
                String file = new URI(url.getFile()).getPath();
                lists.addAll(listFiles(file, packages));
            }
        } catch (Exception e) {
            log.error("fail in scan package :{} ", packages, e);
            throw new StateException("fail in scan packages");
        }
        return lists.stream().filter(this::classFilter).collect(Collectors.toSet());
    }

    private Set<ClassInfo> listFiles(String path, String packageName) {
        Set<ClassInfo> results = new HashSet<>();
        try {
            Files.list(Paths.get(path)).forEach(ph -> {
                if (ph.toFile().isDirectory()) {
                    results.addAll(listFiles(ph.toFile().getAbsolutePath(),
                            packageName + "." + ph.toFile().getName()));
                } else {
                    String fileName = ph.toFile().getName();
                    fileName = fileName.substring(0, fileName.length() - 6);
                    results.add(ClassInfo.builder()
                            .className(packageName + "." + fileName)
                            .build());
                }
            });
        } catch (Exception e) {
            log.error("fail in scan package {}", packageName, e);
        }
        return results;
    }

    abstract boolean classFilter(ClassInfo classInfo);
}
