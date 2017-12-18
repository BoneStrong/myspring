package com.dzz.ioc.context;

import com.dzz.ioc.ApplicationContext;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
public class AnoApplicationContext implements ApplicationContext {

    @Override
    public Object getBeanByName(String name) {
        return null;
    }

    @Override
    public <T> T getBeanByName(String name, Class<T> clazz) {
        return null;
    }
}
