package com.dzz.ioc.pojo;

import com.dzz.ioc.ano.Component;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
@Component
public class People {

    private String userName;

    private int age;

    public String getUserName() {
        return userName;
    }

    public People setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public People setAge(int age) {
        this.age = age;
        return this;
    }

    public void introduceSelf(){
        System.out.println("hello ,i am you baba");
    }
}
