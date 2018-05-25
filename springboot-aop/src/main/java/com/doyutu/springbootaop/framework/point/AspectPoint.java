package com.doyutu.springbootaop.framework.point;

import lombok.Data;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@SuppressWarnings("serial")
@Data
public class AspectPoint {

    //业务bean
    private Object bean = "";
    //业务方法
    private Method method;
    //代理
    private MethodProxy proxy;
    //业务所在class
    private Class<?> clazz;
    //业务方法所需参数
    private Object[] params;
    private AspectPoint childPoint;
    //切面方法
    private Method aspectMethod;
    //切面bean
    private Object aspectBean;

    public Object invoke() throws Throwable {
        if (childPoint == null) {
            return proxy.invokeSuper(bean, params);
        }
        childPoint.setParams(params);
        return childPoint.getAspectMethod().invoke(childPoint.getAspectBean(), childPoint);
    }

}
