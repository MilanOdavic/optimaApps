package com.example.optimaApps.repository;

import com.example.optimaApps.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
