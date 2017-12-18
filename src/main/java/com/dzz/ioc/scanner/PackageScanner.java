package com.dzz.ioc.scanner;

import java.io.IOException;
import java.util.Set;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
public interface PackageScanner {

    /**
     * 获取包下的类全限定名
     *
     * @return
     * @throws IOException
     */
    public Set<String> getFullyQualifiedClassNameSet() throws IOException;
}
