package com.dzz.ioc.processor;

/**
 * @author zoufeng
 * @since 2018/1/4
 * <p>
 * bean 处理器 为bean的生命周期提供回调接口
 */
public interface BeanPostProcessor {

    /**
     * @param var1
     * @param var2
     * @return
     */
    Object postProcessBeforeInitialization(Object var1, String var2);

    /**
     * @param var1
     * @param var2
     * @return
     */
    Object postProcessAfterInitialization(Object var1, String var2);
}
