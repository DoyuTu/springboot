package com.doyutu.springbootaop.framework.aspect;

import com.doyutu.springbootaop.framework.annotation.Around;
import com.doyutu.springbootaop.framework.annotation.Component;
import com.doyutu.springbootaop.framework.annotation.LogHead;
import com.doyutu.springbootaop.framework.point.AspectPoint;

/**
 * @author doyutu
 * @date 2018-05-18 16:29
 * springboot
 */
@Component
public class LogAspect {

    @Around(LogHead.class)
    public Object invokePoint(AspectPoint wrapper) throws Throwable {
        System.out.println("LogAspect exec");
        return wrapper.invoke();
    }
}
