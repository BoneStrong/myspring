package com.dzz.ioc.util;

import java.net.URL;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
public class StringUtils {

    /**
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        if (-1 == pos) {
            return fileUrl;
        }

        return fileUrl.substring(5, pos);
    }

    public static String basePackageHandle(String basePackage) {
        if (basePackage == null || basePackage.trim().length() == 0 || "/".equals(basePackage)) {
            return "";
        }
        if (basePackage.startsWith("/") && basePackage.length() > 1) {
            return basePackage.substring(1);
        }
        return basePackage;
    }

    /**
     * "cn.fh.lightning" -> "cn/fh/lightning"
     *
     * @param name
     * @return
     */
    public static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }

    /**
     * "Apple.class" -> "Apple"
     */
    public static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }

        return name;
    }

    /**
     * /application/home -> /home
     *
     * @param uri
     * @return
     */
    public static String trimURI(String uri) {
        String trimmed = uri.substring(1);
        int splashIndex = trimmed.indexOf('/');

        return trimmed.substring(splashIndex);
    }

    /**
     * 首字母大 小写
     *
     * @param str
     * @param up
     * @return
     */
    public static String getStringFristCharUp(String str, boolean up) {
        int len = (str == null ? 0 : str.length());
        if (len <= 0) {
            return str;
        }
        boolean bufferChanged = false;
        StringBuffer tmpSb = new StringBuffer();
        tmpSb.delete(0, tmpSb.length());
        tmpSb.append(str);
        char curCh = str.charAt(0);
        if (curCh >= 'a' && curCh <= 'z') {
            if (up) {
                curCh = (char) (curCh - 'a' + 'A');
            }
            tmpSb.setCharAt(0, curCh);
            bufferChanged = true;
        }
        if (curCh >= 'A' && curCh <= 'Z') {
            if (!up) {
                curCh = (char) (curCh - 'A' + 'a');
            }
            tmpSb.setCharAt(0, curCh);
            bufferChanged = true;
        }

        for (int i = 1; i < len; i++) {
            curCh = str.charAt(i);
            tmpSb.setCharAt(i, curCh);
            bufferChanged = true;
        }
        return (bufferChanged ? tmpSb.toString() : str);
    }

    /**
     * com.dzz.People --> people
     *
     * @param fullName
     * @return
     */
    public static String classFullNameToBeanName(String fullName) {
        if (fullName == null) {
            throw new NullPointerException();
        }
        return getStringFristCharUp(fullName.substring(fullName.lastIndexOf(".") + 1), false);
    }
}
