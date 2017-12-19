package com.dzz.ioc.util;


import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zoufeng
 * @since 2017/12/19
 */
public class AnoUtils {

    private static final List<String> DEFALUT_ANNOTATION_LIST = Arrays.asList(new String[]{Target.class.getName(), Retention.class.getName(), Documented.class.getName()});


    public static boolean isContainAno(Class sourceAnnotation, Class targetAnnotation) {
        Set<Class> allAnnotations = getAllAnnotations(sourceAnnotation, new HashSet<>());
        List<String> collect = allAnnotations.stream().map(x -> x.getName()).collect(Collectors.toList());
        return collect.contains(targetAnnotation.getName());
    }

    public static Set<Class> getAllAnnotations(Class sourceAnnotation, Set<Class> set) {
        if (sourceAnnotation.isAnnotation() && DEFALUT_ANNOTATION_LIST.contains(sourceAnnotation.getName())) {
            return set;
        }
        Annotation[] annotations = sourceAnnotation.getAnnotations();
        if (annotations.length == 0) {
            return set;
        }
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> aClass = annotation.annotationType();
            set.add(aClass);
            getAllAnnotations(aClass, set);
        }
        return set;
    }
}
