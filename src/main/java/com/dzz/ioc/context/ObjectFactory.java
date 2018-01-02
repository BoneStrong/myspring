package com.dzz.ioc.context;

/**
 * @author zoufeng
 * @since 2017/12/28
 */
public interface ObjectFactory<T> {

    /**
     * 自定义获取bean的方式，这里可自定义代理方式
     *
     * @return
     */
    T getObject();
}
