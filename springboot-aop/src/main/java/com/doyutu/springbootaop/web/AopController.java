package com.doyutu.springbootaop.web;

import com.doyutu.springbootaop.fremework.annotation.Component;
import com.doyutu.springbootaop.fremework.annotation.Inject;
import com.doyutu.springbootaop.service.AopService;

/**
 * @author doyutu
 * @date 2018-04-21 16:40
 * springboot
 */
@Component
public class AopController {

    @Inject
    private AopService aopService;

    public void getService() {
        String aop = aopService.getAop();
        System.out.println(aop);
    }

}
