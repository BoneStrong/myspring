package com.dzz.ioc;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
public interface BeanFactory {

    /**
     * getBean
     * @param name
     * @return
     */
    Object getBeanByName(String name);

    /**
     * getBean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getBeanByName(String name, Class<T> clazz);
}
