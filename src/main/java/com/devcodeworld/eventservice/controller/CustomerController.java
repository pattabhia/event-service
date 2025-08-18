package com.devcodeworld.eventservice.controller;

import com.devcodeworld.eventservice.dto.Customer;
import com.devcodeworld.eventservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(value = "/reactive",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersFlux() {
        return customerService.getCustomersByFlux();
    }
}
