package com.dzz.ioc.context;

/**
 * @author zoufeng
 * @since 2017/12/28
 */
public interface ObjectFactory<T> {

    T getObject();
}
