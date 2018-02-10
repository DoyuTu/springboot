package com.doyutu.springbootelasticsearch.controller;

import com.doyutu.springbootelasticsearch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test() throws IOException {
        customerService.saveCustomers();
        customerService.fetchAllCustomers();
        customerService.fetchIndividualCustomers();
        return new ResponseEntity(HttpStatus.OK);
    }
}