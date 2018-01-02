package com.dzz.ioc.util;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zoufeng
 * @since 2017/12/29
 */
public class ProxyClassPrintUtils {

    /**
     * @param name  生成的类文件名
     * @param clazz 需要打印输出的类Class对象
     * @throws IOException
     */
    public static void print(String name, Class clazz) throws IOException {
        String path = clazz.getResource(".").getPath();
        byte[] bytes = ProxyGenerator.generateProxyClass(name, clazz.getInterfaces());
        FileOutputStream out = null;
        try {

            out = new FileOutputStream(path.concat(name).concat(".class"));
            out.write(bytes);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
