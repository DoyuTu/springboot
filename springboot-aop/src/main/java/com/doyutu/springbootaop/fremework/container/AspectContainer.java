package com.doyutu.springbootaop.fremework.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author doyutu
 * @date 2018-05-19 13:27
 * springboot
 */
public class AspectContainer {
    /** key 拦截注解 value 拦截器方法 **/
    public static final Map<Class<? extends Annotation>, Method> aspectMap = new ConcurrentHashMap<>();

}
