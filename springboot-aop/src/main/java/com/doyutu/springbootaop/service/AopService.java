package com.doyutu.springbootaop.service;


import com.doyutu.springbootaop.framework.annotation.Component;
import com.doyutu.springbootaop.framework.annotation.LogHead;

/**
 * @author doyutu
 * @date 2018-04-21 16:40
 * springboot
 */
@Component
public class AopService {

    @LogHead
    public String getAop() {
        return "Inject";
    }
}
