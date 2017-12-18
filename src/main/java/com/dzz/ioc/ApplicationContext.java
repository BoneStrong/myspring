package com.dzz.ioc;

/**
 * @author zoufeng
 * @since 2017/12/18
 *
 * 目标：
 * 简单实现IOC容器：
 *  1.实现bean的生命周期管理
 *  2.bean的类型管理 （单例，多例）
 *  3.功能的扩展 实现java配置类注入
 */
public interface ApplicationContext {

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
