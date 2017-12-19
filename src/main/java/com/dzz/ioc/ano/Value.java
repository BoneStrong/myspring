package com.dzz.ioc.ano;

import java.lang.annotation.*;

/**
 * @author zoufeng
 * @since 2017/12/19
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value() default "";
}
