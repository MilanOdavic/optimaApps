package com.example.optimaApps.service;

import com.example.optimaApps.model.Invoice;
import com.example.optimaApps.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetInvoice {

    private final InvoiceRepository invoiceRepository;

    public GetInvoice(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Invoice execute(UUID id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
