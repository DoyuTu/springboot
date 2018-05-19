package com.doyutu.springbootaop.fremework.container;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author doyutu
 * @date 2018-05-19 13:27
 * springboot
 */
public class AspectContainer {

    public static final Map<String, Set<Method>> aspectMap = new ConcurrentHashMap<>();

}
