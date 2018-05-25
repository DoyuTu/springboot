package com.doyutu.springbootaop.web.controller;

import com.doyutu.springbootaop.framework.annotation.Component;
import com.doyutu.springbootaop.framework.annotation.Inject;
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

    public String getService() {
        return aopService.getAop();
    }

}
