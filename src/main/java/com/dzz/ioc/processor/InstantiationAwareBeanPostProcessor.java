package com.dzz.ioc.processor;

/**
 * @author zoufeng
 * @since 2018/1/4
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * bean初始化前处理器
     * @param var1
     * @param var2
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> var1, String var2) ;

    /**
     * bean初始化后处理器
     * @param var1
     * @param var2
     * @return
     */
    boolean postProcessAfterInstantiation(Object var1, String var2);

//    PropertyValues postProcessPropertyValues(PropertyValues var1, PropertyDescriptor[] var2, Object var3, String var4);
}
