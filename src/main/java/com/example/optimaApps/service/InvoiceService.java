package com.example.optimaApps.service;

import com.example.optimaApps.dto.InvoiceDto;
import com.example.optimaApps.dto.InvoiceItemDto;
import com.example.optimaApps.dto.InvoiceResponseDto;
import com.example.optimaApps.model.Customer;
import com.example.optimaApps.model.Invoice;
import com.example.optimaApps.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final GetCustomer getCustomer;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, GetCustomer getCustomer) {
        this.invoiceRepository = invoiceRepository;
        this.getCustomer = getCustomer;
    }

    public List<InvoiceResponseDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();

        return invoices.stream()
                .map(invoice -> InvoiceResponseDto.builder()
                        .id(invoice.getId())
                        .invoiceNumber(invoice.getInvoiceNumber())
                        .issueDate(invoice.getIssueDate())
                        .customerId(invoice.getCustomer().getId())
                        .totalAmount(invoice.getTotalAmount())
                        .invoiceItems(
                                invoice.getInvoiceItems().stream().map(invoiceItem -> InvoiceItemDto.builder()
                                        .id(invoiceItem.getId())
                                        .invoiceId(invoiceItem.getInvoice().getId())
                                        .productId(invoiceItem.getProduct().getId())
                                        .quantity(invoiceItem.getQuantity())
                                        .amount(invoiceItem.getAmount())
                                        .build()).collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }

    public InvoiceResponseDto getInvoiceById(UUID id) {
        Optional<Invoice> invoiceData = invoiceRepository.findById(id);
        if(!invoiceData.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found by provided id");
        }
        Invoice invoice = invoiceData.get();
        return InvoiceResponseDto.builder()
                .id(invoice.getId())
                .invoiceNumber(invoice.getInvoiceNumber())
                .issueDate(invoice.getIssueDate())
                .customerId(invoice.getCustomer().getId())
                .totalAmount(invoice.getTotalAmount())
                .invoiceItems(
                        invoice.getInvoiceItems().stream().map(invoiceItem -> InvoiceItemDto.builder()
                                .id(invoiceItem.getId())
                                .invoiceId(invoiceItem.getInvoice().getId())
                                .productId(invoiceItem.getProduct().getId())
                                .quantity(invoiceItem.getQuantity())
                                .amount(invoiceItem.getAmount())
                                .build()).collect(Collectors.toList())
                )
                .build();
    }

    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {

        Customer customer = getCustomer.execute(invoiceDto.getCustomerId());

        final Invoice invoice = Invoice.builder()
                .invoiceNumber(invoiceDto.getInvoiceNumber())
                .issueDate(invoiceDto.getIssueDate())
                .customer(customer)
                .totalAmount(invoiceDto.getTotalAmount())
                .build();

        invoiceRepository.save(invoice);

        return InvoiceDto.builder()
                .id(invoice.getId())
                .invoiceNumber(invoice.getInvoiceNumber())
                .issueDate(invoice.getIssueDate())
                .customerId(invoice.getCustomer().getId())
                .totalAmount(invoice.getTotalAmount())
                .build();
    }

    @Transactional
    public void updateInvoice(UUID id, InvoiceDto invoiceDto) {
        Optional<Invoice> invoiceData = invoiceRepository.findById(id);
        if (invoiceData.isPresent()) {
            final Customer customer = getCustomer.execute(invoiceDto.getCustomerId());
            final Invoice invoice = Invoice.builder()
                .id(id)
                    .invoiceNumber(invoiceDto.getInvoiceNumber())
                    .issueDate(invoiceDto.getIssueDate())
                    .totalAmount(invoiceDto.getTotalAmount())
                    .customer(customer)
                .build();
            invoiceRepository.save(invoice);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found by provided id");
        }
    }

    public void deleteInvoice(UUID id) {
        invoiceRepository.deleteById(id);
    }

}
