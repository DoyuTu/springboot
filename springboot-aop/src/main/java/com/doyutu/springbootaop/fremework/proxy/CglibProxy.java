package com.doyutu.springbootaop.fremework.proxy;

import com.doyutu.springbootaop.fremework.container.BeanContainer;
import com.doyutu.springbootaop.fremework.entity.AspectEntity;
import com.doyutu.springbootaop.fremework.init.InitContext;
import com.doyutu.springbootaop.fremework.point.AspectPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * CGLIB代理
 * @author doyutu
 * @date 2018-04-18 10:20
 * springboot
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {

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
        Class<?> aClass = method.getDeclaringClass();
        String name = aClass.getName();
        if (!InitContext.INTERCEPT_MAP.containsKey(name)) {
            return invokeSuper;
        }
        AspectPoint point = getAspectPoint(method, aClass, name, methodProxy);
        if (point == null) {
            return methodProxy.invokeSuper(o, objects);
        }
        point.setParams(objects);
        return point.getAspectMethod().invoke(point.getAspectBean(),
                point);
    }

    private AspectPoint getAspectPoint(Method method, Class<?> aClass, String name, MethodProxy methodProxy) {
        if (InitContext.METHOD_INTERCEPT_MAP.containsKey(method)) {
            return InitContext.METHOD_INTERCEPT_MAP.get(method);
        }
        List<AspectEntity> entity = new ArrayList<>(InitContext.INTERCEPT_MAP.get(name));
        AspectPoint point = new AspectPoint();
        Object bean = BeanContainer.getBean(aClass);
        point.setClazz(aClass);
        point.setBean(bean);
        point.setProxy(methodProxy);
        Method aspectMethod = entity.get(0).getAspectInvokeMethod();
        point.setAspectBean(BeanContainer.getBean(aspectMethod.getDeclaringClass()));
        point.setAspectMethod(aspectMethod);
        point.setMethod(method);
        entity.remove(0);
        AspectPoint parseAspect = parseAspect(point, entity);
        if (parseAspect != null) {
            point.setChildPoint(parseAspect);
        }
        InitContext.METHOD_INTERCEPT_MAP.put(method, point);
        return point;
    }

    private AspectPoint parseAspect(AspectPoint basePoint, List<AspectEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        Method invokeMethod = entityList.get(0).getAspectInvokeMethod();
        entityList.remove(0);
        Object bean = BeanContainer.getBean(invokeMethod.getDeclaringClass());
        basePoint.setAspectMethod(invokeMethod);
        basePoint.setAspectBean(bean);
        AspectPoint point = parseAspect(basePoint, entityList);
        if (point != null) {
            basePoint.setChildPoint(point);
            return basePoint;
        }
        return basePoint;
    }

}
