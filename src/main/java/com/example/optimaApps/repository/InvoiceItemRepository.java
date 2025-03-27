package com.example.optimaApps.repository;

import com.example.optimaApps.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, UUID> {

    @Query(value = "SELECT ii.* " +
            "FROM invoice_item ii " +
            "JOIN invoice i ON i.id = ii.invoice_id " +
            "WHERE i.id = :invoiceId" +
            ";", nativeQuery = true)
    List<InvoiceItem> findAllInvoiceItemsByInvoiceId(UUID invoiceId);

}
