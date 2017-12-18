package com.dzz.ioc.context;

import org.junit.Test;

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



}