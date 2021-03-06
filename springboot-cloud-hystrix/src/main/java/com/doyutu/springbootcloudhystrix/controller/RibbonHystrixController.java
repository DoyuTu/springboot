package com.doyutu.springbootcloudhystrix.controller;

import com.doyutu.springbootcloudhystrix.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RibbonHystrixController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/")
    public String ribbonController() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getServiceId() + "/dc";
        System.out.println(url);
        return this.ribbonService.getService(url);
    }
}
