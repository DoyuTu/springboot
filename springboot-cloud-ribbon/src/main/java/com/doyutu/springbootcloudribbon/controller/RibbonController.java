package com.doyutu.springbootcloudribbon.controller;

import com.doyutu.springbootcloudribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/ribbon")
    public String ribbonController() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = serviceInstance.getUri() + "/dc";
        System.out.println(url);
        return this.ribbonService.getService(url);
    }
}
