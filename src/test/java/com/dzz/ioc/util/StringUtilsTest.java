package com.dzz.ioc.util;

import com.dzz.ioc.pojo.People;
import com.dzz.ioc.scanner.ClasspathPackageScanner;
import org.junit.Test;

import java.net.URL;
import java.util.Set;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
public class StringUtilsTest {
    @Test
    public void getRootPath() throws Exception {
//        System.out.println(StringUtils.getRootPath(new URL("file:/home/whf/cn/fh")));
        System.out.println(StringUtils.getRootPath(new URL("jar:file:/E:/application/idea201701/IntelliJ%20IDEA%202017.1/lib/idea_rt.jar!/com")));
    }

    @Test
    public void dotToSplash() throws Exception {
        Set<String> fullyQualifiedClassNameSet = new ClasspathPackageScanner("/").getFullyQualifiedClassNameSet();
        System.out.println(fullyQualifiedClassNameSet);

    }

    @Test
    public void trimExtension() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        String name = People.class.getName();
        Class<?> aClass = classLoader.loadClass(name);
        System.out.println(aClass);
    }

    @Test
    public void trimURI() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("");
        System.out.println(resource);
        String s = resource.getPath() + "com";
        URL com = classLoader.getResource("com");
        System.out.println(com.getProtocol()+com.getPath());
//        System.out.println(result);

    }

}