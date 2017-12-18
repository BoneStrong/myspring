package com.dzz.ioc.ano;

import java.lang.annotation.*;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {

    String name() default "";

    boolean autowire() default true;

    String initMethod() default "";

    String destoryMethod() default "";
}
