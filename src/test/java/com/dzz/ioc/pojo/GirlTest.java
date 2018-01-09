package com.dzz.ioc.pojo;

import org.junit.Test;

/**
 * @author zoufeng
 * @since 2018/1/4
 */
public class GirlTest {
    @Test
    public void test(){
        Girl shishi = new Shishi();
        Shishi shishi2 = new Shishi();
        System.out.println(shishi instanceof Shishi);
        System.out.println(shishi instanceof Girl);
        System.out.println(shishi2 instanceof Shishi);
        System.out.println(shishi2 instanceof Girl);

    }
}
