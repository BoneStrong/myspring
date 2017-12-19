package com.dzz.ioc.context;


import com.dzz.ioc.BeanFactory;
import com.dzz.ioc.ano.Component;
import com.dzz.ioc.scanner.ClasspathPackageScanner;
import com.dzz.ioc.scanner.PackageScanner;
import com.dzz.ioc.util.AnoUtils;
import com.dzz.ioc.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zoufeng
 * @since 2017/12/18
 * <p>
 * 自定义注解beanFactory
 * <p>
 * 1.扫描路径获取需要管理的bean  Component Configuration
 * 2.根据bean的属性进行bean配置
 * 3.反射获取bean的代理类，实现动态代理
 * <p>
 * 解析bean的几个问题
 * 单例管理 factoryBean
 * 别名管理
 * 依赖注入（循环依赖问题） ：状态记录集合，factoryBean 作为中间代理提供bean
 * <p>
 * 使用线程上下文加载器来加载类文件
 */
public class DefaultBeanFactory implements BeanFactory {

    private static Map<String, Object> beanNameMap = new ConcurrentHashMap<>();

    private static Map<String, Class> beanNameClassMap = new ConcurrentHashMap<>();

    private static Map<String, Object> beanStateMap = new ConcurrentHashMap<>();

    private static ClassLoader classLoader;

    private PackageScanner packageScanner;

    public DefaultBeanFactory() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        classLoader = Thread.currentThread().getContextClassLoader();
        this.packageScanner = new ClasspathPackageScanner(classLoader);
        initMethod();
    }

    private void initMethod() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        loaderBeanClass();
        createNoParamBean();
        beanDiHandle();
    }

    private void loaderBeanClass() throws IOException, ClassNotFoundException {
        Set<String> fullyQualifiedClassNameSet = packageScanner.getFullyQualifiedClassNameSet();
        for (String s : fullyQualifiedClassNameSet) {
            Class<?> clazz = classLoader.loadClass(s);
            if (clazz.isAnnotation() || clazz.isInterface()){
                continue;
            }
            if (AnoUtils.isContainAno(clazz,Component.class)){
                String beanName = StringUtils.classFullNameToBeanName(s);
                //loader Class
                beanNameClassMap.put(beanName, clazz);
            }
        }
    }

    private void createNoParamBean() throws IllegalAccessException, InstantiationException {
        for (Map.Entry<String, Class> entry : beanNameClassMap.entrySet()) {
            Object o = entry.getValue().newInstance();
            System.out.println(o);
            //di handle  and to resolve circle
            beanNameMap.put(entry.getKey(), o);
        }
    }

    private void beanDiHandle() {

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
