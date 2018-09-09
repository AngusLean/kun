package com.doubleysoft.kun.scanner;

import com.doubleysoft.kun.context.ClassInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by anguslean
 * 18-9-9 下午3:06
 */
public class ClassPathBeanScannerImplTest {
    private Scanner scanner;

    @Before
    public void setUp() throws Exception {
        scanner = new ClassPathBeanScannerImpl();
    }

    @Test
    public void scan() {
        Set<ClassInfo> results = scanner.scan("com.doubleysoft.kun.scanner.test");
        Assert.assertEquals(results.size(), 2);
    }

    @Test
    public void testListFiles() throws IOException {
        Files.list(Paths.get("/home/anguslean/project/java/kun/src/main/java/com/doubleysoft/kun"))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
    }

    @Test
    public void testFindClass() {
        try {
            Class.forName("com.doubleysoft.kun.KunConfig");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}