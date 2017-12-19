package com.dzz.ioc.util;

import com.dzz.ioc.ano.Component;
import com.dzz.ioc.pojo.ComplexGroup;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zoufeng
 * @since 2017/12/19
 */
public class AnoUtilsTest {
    @Test
    public void isContainAno() throws Exception {
        Class<ComplexGroup> complexGroupClass = ComplexGroup.class;
        boolean containAno = AnoUtils.isContainAno(complexGroupClass, Component.class);
        System.out.println(containAno);
    }

    @Test
    public void getAllAnnotations() throws Exception {
        Set<Class> allAnnotations = AnoUtils.getAllAnnotations(ComplexGroup.class, new HashSet<>());
        System.out.println(allAnnotations);
    }

}