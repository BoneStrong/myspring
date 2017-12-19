package com.dzz.ioc.context;

import com.dzz.ioc.ano.Component;
import com.dzz.ioc.pojo.ComplexGroup;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
public class DefaultBeanFactoryTest {
    @Test
    public void getBeanByName() throws Exception {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        System.out.println(defaultBeanFactory);

        System.out.println(System.getProperty("user.dir"));
    }
    @Test
    public void test(){
        ComplexGroup complexGroup = new ComplexGroup();
        Class<? extends ComplexGroup> aClass = complexGroup.getClass();
        Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
        AnnotatedType[] annotatedInterfaces = aClass.getAnnotatedInterfaces();
        AnnotatedType annotatedSuperclass = aClass.getAnnotatedSuperclass();
        Annotation[] annotations = aClass.getAnnotations();
        Class<?> componentType = aClass.getComponentType();
        System.out.println("over");

    }
    @Test
    public void test2(){
        ComplexGroup complexGroup = new ComplexGroup();
        Class<? extends ComplexGroup> aClass = complexGroup.getClass();
        Class<?>[] classes = aClass.getClasses();
        System.out.println(1);
    }



}