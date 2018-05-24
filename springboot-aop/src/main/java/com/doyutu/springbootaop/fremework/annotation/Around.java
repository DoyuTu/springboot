package com.doyutu.springbootaop.fremework.annotation;

import java.lang.annotation.*;

/**
 * @author doyutu
 * @date 2018-04-18 10:41
 * springboot
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Around {

    Class<? extends Annotation> [] value() default {};
}
