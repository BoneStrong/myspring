package com.dzz.ioc.definition;

/**
 * @author zoufeng
 * @since 2018/1/4
 */
public interface BeanDefinition {

    void setDependsOn(String... var1);

    String[] getDependsOn();

    void setParentName(String var1);

    String getParentName();

    void setBeanClassName(String var1);

    String getBeanClassName();

    Class getBeanClass();

    void setBeanClass(Class beanClass);
}
