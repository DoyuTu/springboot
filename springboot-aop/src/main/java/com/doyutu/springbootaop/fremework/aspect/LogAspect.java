package com.doyutu.springbootaop.fremework.aspect;

import com.doyutu.springbootaop.fremework.annotation.Around;
import com.doyutu.springbootaop.fremework.annotation.Component;
import com.doyutu.springbootaop.fremework.annotation.LogHead;
import com.doyutu.springbootaop.fremework.point.AspectPoint;

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
