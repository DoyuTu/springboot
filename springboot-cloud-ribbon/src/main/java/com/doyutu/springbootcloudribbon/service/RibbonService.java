package com.doyutu.springbootcloudribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param url 必须为ServiceID
     * @return Response
     */
    public String getService(String url) {
        return this.restTemplate.getForObject(url, String.class);
    }

}
