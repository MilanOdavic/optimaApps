package com.example.optimaApps.repository;

import com.example.optimaApps.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
