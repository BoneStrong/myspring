package com.dzz.ioc.scanner;

import com.dzz.ioc.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author zoufeng
 * @since 2017/12/18
 * <p>
 * 包扫描：获取指定包下的类全限定名
 * <p>
 * 有的web server在部署运行时会解压jar包，因此class文件会在普通的文件目录下。
 * 如果web server不解压jar包，则class文件会直接存在于Jar包中。
 * 对于前者，只需定位到class文件所在目录，然后将class文件名读取出即可；
 * 对于后者，则需先定位到jar包所在目录，然后使用JarInputStream读取Jar包，得到class类名。
 * <p>
 * 复习
 * 类加载的过程
 */
public class ClasspathPackageScanner implements PackageScanner {

    private String basePackage;

    private ClassLoader cl;

    private Set<String> fullyQualifiedClassNameSet = new HashSet<>();

    public ClasspathPackageScanner() {
        this.basePackage = null;
        this.cl = getClass().getClassLoader();
    }

    public ClasspathPackageScanner(ClassLoader cl) {
        this.basePackage = null;
        this.cl = cl;
    }

    public ClasspathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }

    public ClasspathPackageScanner(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }

    /**
     * @param basePackage
     * @param nameSet
     * @return
     * @throws IOException
     */
    private Set<String> doScan(String basePackage, Set<String> nameSet) throws IOException {
        basePackage = StringUtils.basePackageHandle(basePackage);
        String splashPath = StringUtils.dotToSplash(basePackage);
        URL url = new URL(cl.getResource("") + splashPath);
        String filePath = StringUtils.getRootPath(url);
        List<String> names = null;
        if (isJarFile(filePath)) {
            names = readFromJarFile(filePath, splashPath);
        } else {
            names = readFromDirectory(filePath);
        }
        if (names != null) {
            for (String name : names) {
                if (isClassFile(name)) {
                    nameSet.add(toFullyQualifiedName(name, basePackage));
                } else {
                    if (basePackage.length() == 0) {
                        doScan(basePackage + name, nameSet);
                    } else {
                        doScan(basePackage + "." + name, nameSet);
                    }
                }
            }
        }
        return nameSet;
    }

    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtils.trimExtension(shortName));
        return sb.toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        List<String> nameList;
        try (JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath))) {
            JarEntry entry = jarIn.getNextJarEntry();
            nameList = new ArrayList<String>();
            while (null != entry) {
                String name = entry.getName();
                if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                    nameList.add(name);
                }

                entry = jarIn.getNextJarEntry();
            }
        }
        return nameList;
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    @Override
    public Set<String> getFullyQualifiedClassNameSet() throws IOException {
        return doScan(basePackage, fullyQualifiedClassNameSet);
    }
}
