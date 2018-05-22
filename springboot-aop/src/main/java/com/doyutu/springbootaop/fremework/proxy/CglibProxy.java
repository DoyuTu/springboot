package com.doyutu.springbootaop.fremework.proxy;

import com.doyutu.springbootaop.fremework.container.BeanContainer;
import com.doyutu.springbootaop.fremework.entity.AspectEntity;
import com.doyutu.springbootaop.fremework.init.InitContext;
import com.doyutu.springbootaop.fremework.point.AspectPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
    private static final Map<Method, AspectEntity> interceptMap = new ConcurrentHashMap<>();

    private static final Map<Method, Object> methodMap = new ConcurrentHashMap<>();

    public Object getProxy(Class<?> cls) {
        Integer modifier = cls.getModifiers();
        if (Modifier.isAbstract(modifier)) {
            return null;
        }
        if (Modifier.isInterface(modifier)) {
            return null;
        }
        if (InitContext.INTERCEPT_MAP.get(cls.getName()) == null) {
            try {
                return cls.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
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
        AspectEntity entity = interceptMap.get(method);
        AspectPoint point = new AspectPoint();
        point.setParams(objects);
        AspectPoint bean = BeanContainer.getBean(o.getClass());
        if (bean == null) {
            return invokeSuper;
        }
        bean.setParams(objects);
        return bean.getAspectMethod().invoke(bean.getAspectBean(),
                bean);
    }

    public static void putIntercept(Method key, AspectEntity value) {
        interceptMap.put(key, value);
    }

}
