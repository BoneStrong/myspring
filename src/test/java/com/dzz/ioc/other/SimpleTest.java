package com.dzz.ioc.other;

import org.junit.Test;

/**
 * @author zoufeng
 * @since 2017/12/29
 */
public class SimpleTest {
    @Test
    public void tryCatchTest(){
        System.out.println("最终结果是："+getInt()); //3
        //说明无论有没有 return--->> finally果然是最屌的
    }

    private int getInt(){
        int i=1;
        try {
            i=1/0;
        } catch (Exception e) {
            i=2;
            System.out.println(i);
            e.printStackTrace();
            return i;
        } finally {
            i=3;
            System.out.println(i);
            return i;
        }
    }
}
