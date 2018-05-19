package com.doyutu.springbootaop.fremework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author doyutu
 * @date 2018-05-18 19:35
 * springboot
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogHead {

    String value() default "";

}
