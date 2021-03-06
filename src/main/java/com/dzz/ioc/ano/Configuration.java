package com.dzz.ioc.ano;

import java.lang.annotation.*;

/**
 * @author zoufeng
 * @since 2017/12/18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {

    String value() default "";
}
