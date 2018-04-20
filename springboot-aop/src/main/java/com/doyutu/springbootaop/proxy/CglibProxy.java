package com.doyutu.springbootaop.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CGLIB代理
 * @author doyutu
 * @date 2018-04-18 10:20
 * springboot
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {

    private static final Map<Method, Object> interceptMap = new ConcurrentHashMap<>();

    private static final Map<Method, Object> methodMap = new ConcurrentHashMap<>();

    public Object getProxy(Class<?> cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        if (!interceptMap.containsKey(method)) {
            return invokeSuper;
        }
        return invokeSuper;
    }

}
