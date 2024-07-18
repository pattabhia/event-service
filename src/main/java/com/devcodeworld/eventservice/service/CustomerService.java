package com.devcodeworld.eventservice.service;

import com.devcodeworld.eventservice.dao.CustomerDao;
import com.devcodeworld.eventservice.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    public Flux<Customer> getCustomersByFlux() {
        return customerDao.getCustomersByFlux();
    }
}
