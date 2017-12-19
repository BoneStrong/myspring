package com.dzz.ioc.util;

import com.dzz.ioc.pojo.Shishi;
import org.junit.Test;

/**
 * @author zoufeng
 * @since 2017/12/19
 */
public class PojoTest {
    @Test
    public void test(){
        Shishi shishi = new Shishi();
        String name = shishi.getName();
        System.out.println(name);
    }
}
