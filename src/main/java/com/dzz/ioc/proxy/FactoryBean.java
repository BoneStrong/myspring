package com.dzz.ioc.proxy;

public interface FactoryBean<T> {

    T getObject();

    Class<?> getObjectType();

    boolean isSingleton();

}
