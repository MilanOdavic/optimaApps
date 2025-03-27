package com.example.optimaApps.service;

import com.example.optimaApps.model.Customer;
import com.example.optimaApps.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetCustomer {

    private final CustomerRepository customerRepository;

    public GetCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer execute(UUID id) {
        return customerRepository.findById(id).orElseThrow();
    }

}
