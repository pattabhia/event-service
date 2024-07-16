package com.devcodeworld.eventservice.dao;

import com.devcodeworld.eventservice.dto.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.range(0, 50)
                .peek(i -> sleep(1000))
                .peek(i -> System.out.println("processing " + i))
                .mapToObj(i -> new Customer(i, "Customer " + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersByFlux() {
        return Flux.range(0, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing " + i))
                .map(x -> new Customer(x, "Customer " + x));
    }
}
