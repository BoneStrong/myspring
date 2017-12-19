package com.dzz.ioc;

import com.dzz.ioc.context.DefaultBeanFactory;
import com.dzz.ioc.pojo.People;

import java.io.IOException;

/**
 * @author zoufeng
 * @since 2017/12/19
 */
public class MySpringApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        BeanFactory beanFactory = new DefaultBeanFactory();
        People people = beanFactory.getBeanByName("people", People.class);
        people.introduceSelf();
    }
}
