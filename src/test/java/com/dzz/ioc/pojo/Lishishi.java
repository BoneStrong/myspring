package com.dzz.ioc.pojo;

/**
 * @author zoufeng
 * @since 2017/12/29
 */
public class Lishishi {

    private String name;

    public String getName() {
        System.out.println("获取名字");
        return name;
    }

    public Lishishi setName(String name) {
        System.out.println("设置名字");
        this.name = name;
        return this;
    }
}
