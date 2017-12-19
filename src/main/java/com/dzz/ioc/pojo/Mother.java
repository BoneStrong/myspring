package com.dzz.ioc.pojo;

import com.dzz.ioc.ano.Autowired;
import com.dzz.ioc.ano.Component;

import javax.annotation.Resource;

/**
 * @author zoufeng
 * @since 2017/12/19
 */
@Component
public class Mother {

    @Resource
    private String name;

    @Autowired
    private Son son;


}
