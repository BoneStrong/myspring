package com.dzz.ioc.pojo;

import com.dzz.ioc.ano.Autowired;
import com.dzz.ioc.ano.Component;

import javax.annotation.Resource;

/**
 * @author zoufeng
 * @since 2017/12/19
 */
@Component
public class Father {

    @Resource(name = "dzz")
    private String name;

    @Autowired
    private Mother mother;
}
