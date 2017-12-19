package com.dzz.ioc.context;


import com.dzz.ioc.BeanFactory;
import com.dzz.ioc.scanner.ClasspathPackageScanner;
import com.dzz.ioc.scanner.PackageScanner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zoufeng
 * @since 2017/12/18
 * <p>
 * 自定义注解beanFactory
 * 1.扫描路径获取需要管理的bean  Component Configuration
 * 2.根据bean的属性进行bean配置
 * 3.反射获取bean的代理类，实现动态代理
 */
public class DefaultBeanFactory implements BeanFactory {

    private static Map<String, Object> beanNameMap = new ConcurrentHashMap<>();

    private PackageScanner packageScanner;

    public DefaultBeanFactory() {
        this.packageScanner = new ClasspathPackageScanner();
    }

    @Override
    public Object getBeanByName(String name) {
        return beanNameMap.get(name);
    }

    @Override
    public <T extends Object> T getBeanByName(String name, Class<T> clazz) {
        Object o = beanNameMap.get(name);
        if (o == null) {
            throw new NullPointerException();
        }
        T t = null;
        try {
            t = (T) o;
        } catch (Exception e) {
            throw new ClassCastException(String.format(" %s can not cast of %s", o.getClass().getName(), clazz.getName()));
        }
        return t;
    }

}
