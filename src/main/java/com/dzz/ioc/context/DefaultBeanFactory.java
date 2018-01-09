package com.dzz.ioc.context;


import com.dzz.ioc.BeanFactory;
import com.dzz.ioc.ano.Component;
import com.dzz.ioc.definition.BeanDefinition;
import com.dzz.ioc.processor.BeanPostProcessor;
import com.dzz.ioc.scanner.ClasspathPackageScanner;
import com.dzz.ioc.scanner.PackageScanner;
import com.dzz.ioc.util.AnoUtils;
import com.dzz.ioc.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zoufeng
 * @since 2017/12/18
 * <p>
 * beanFactory 默认实现类
 * <p>
 * <p>
 * 1.扫描路径获取需要管理的bean  Component Configuration
 * 2.根据bean的属性进行bean配置
 * 3.反射获取bean的代理类，实现动态代理
 * <p>
 * 使用线程上下文加载器来加载类文件
 * <p>
 * <p>
 * 一些简单的概念问题：
 * spring 管理的bean有factory-bean 和 factory-method 这两个属性，
 * 本质其实是生成一个ObjectFatory对象，然后通过静态工厂方式创建bean对象
 * 【这个和FactoryBean 没有关系】
 * <p>
 * look-up属性 ： 为abstract或interface 生成一个代理类，注入实现了指定方法的子类
 * <p>
 * <p>
 * spring 的MethodOverride处理 其实只是处理lookup-Metod和replace-Method这两个属性
 * <p>
 * 默认按类型注入
 */
public class DefaultBeanFactory implements BeanFactory {
    /**
     * 默认该项目所有bean均为单例模式
     * 1单例bean获取的三级缓存  singletonObjects --> earlySingletonObjects  --> singletonFactories
     * 1.1beanFactory 调用getBean时 singletonObjects，
     * 1.2没有获取则从 earlySingletonObjects 中获取 object
     * 1.3还没有则从 singletonFactories 中获取 ObjectFactory 调用 getObeject()
     * <p>
     * 2.三级缓存均没有获取bean 则进行bean创建流程：
     * bean的创建过程三级缓存用于解决循环依赖
     * 2.1 bean获取 BeanDefinition 反射生成原始bean对象
     * 2.2 BeanPostProcessor InstantiationAwareBeanPostProcessor 进行bean特殊处理 例如将bean进行AOP代理 等等
     * 2.3 属性注入 假设A,B,C三个bean均未初始化 其中 A需要引用B  B需要引用C C需要引用A
     * 开始初始化A 生成ObjectFactoryA 加入singletonFactories  并将objectFactory.getObject()获取的beanA put earlySingletonObjects
     * A注入B时发现无法获取单利B 开始初始化B 走2.2 同理注入B需要注入C，开始初始化C注入A时可以从earlySingletonObjects获取 A的引用，完成C对象的生成
     * 这时B再C初始化完成后可以注入C 完成B的初始化 ，接着A完成注入B的动作，依赖全部注入完毕
     * 2.4 类型检查 如果ok返回单例bean
     */

    private static Map<String, Object> singletonObjects = new ConcurrentHashMap<>(64);

    private static Map<String, Object> singletonFactories = new ConcurrentHashMap<>(64);

    private static Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(64);


    /**
     * beanFactory初始化时扫描所有的beanPostProcessor
     */
    private List<BeanPostProcessor> beanPostProcessors;
    /**
     * 自定义beanDefinition 池 这里只放入非interface,enum的类文件  beanName --> BeanDefinition
     */
    private static Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(64);

    /**
     * 创建中的beanMap  beanName --> Bean
     */
    private static Map<String, Object> currentCreateBeans = new ConcurrentHashMap<>(64);

    private static ClassLoader classLoader;

    private PackageScanner packageScanner;

    public DefaultBeanFactory() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        classLoader = Thread.currentThread().getContextClassLoader();
        this.packageScanner = new ClasspathPackageScanner(classLoader);
        initMethod();
    }

    private void initMethod() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //获取bean字节码文件，定义beanDefinition
        loaderBeanClass();

    }

    private void loaderBeanClass() throws IOException, ClassNotFoundException {
        Set<String> fullyQualifiedClassNameSet = packageScanner.getFullyQualifiedClassNameSet();
        for (String s : fullyQualifiedClassNameSet) {
            Class<?> clazz = classLoader.loadClass(s);
            //枚举，注解，接口不加载
            if (clazz.isAnnotation() || clazz.isInterface()||clazz.isEnum()) {
                continue;
            }
            if (AnoUtils.isContainAno(clazz, Component.class)) {
                String beanName = StringUtils.classFullNameToBeanName(s);
                //loader Class and  create BeanDefinition

            }
        }
    }





    private void populateBean() {

    }

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public <T extends Object> T getBean(String name, Class<T> clazz) {
       /* Object o = beanNameMap.get(name);
        if (o == null) {
            throw new NullPointerException();
        }
        T t = null;
        try {
            t = (T) o;
        } catch (Exception e) {
            throw new ClassCastException(String.format(" %s can not cast of %s", o.getClass().getName(), clazz.getName()));
        }*/
        return null;
    }

}
