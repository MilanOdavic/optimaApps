package com.example.optimaApps.service;

import com.example.optimaApps.dto.InvoiceItemDto;
import com.example.optimaApps.model.Invoice;
import com.example.optimaApps.model.InvoiceItem;
import com.example.optimaApps.model.Product;
import com.example.optimaApps.repository.InvoiceItemRepository;
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
public class InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;
    private final GetInvoice getInvoice;
    private final GetProduct getProduct;

    @Autowired
    public InvoiceItemService(InvoiceItemRepository invoiceItemRepository, GetInvoice getInvoice, GetProduct getProduct) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.getInvoice = getInvoice;
        this.getProduct = getProduct;
    }

    public List<InvoiceItemDto> getAllInvoiceItems(UUID invoiceId) {
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAllInvoiceItemsByInvoiceId(invoiceId);

        return invoiceItems.stream()
                .map(invoiceItem -> InvoiceItemDto.builder()
                        .id(invoiceItem.getId())
                        .invoiceId(invoiceItem.getInvoice().getId())
                        .productId(invoiceItem.getProduct().getId())
                        .amount(invoiceItem.getAmount())
                        .quantity(invoiceItem.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    public InvoiceItemDto getInvoiceItemById(UUID id) {
        Optional<InvoiceItem> invoiceItemData = invoiceItemRepository.findById(id);
        if(!invoiceItemData.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found by provided id");
        }
        InvoiceItem invoiceItem = invoiceItemData.get();
        return InvoiceItemDto.builder()
                .id(invoiceItem.getId())
                .invoiceId(invoiceItem.getInvoice().getId())
                .productId(invoiceItem.getProduct().getId())
                .amount(invoiceItem.getAmount())
                .quantity(invoiceItem.getQuantity())
                .build();
    }

    public InvoiceItemDto createInvoiceItem(InvoiceItemDto invoiceItemDto) {

        final Invoice invoice = getInvoice.execute(invoiceItemDto.getInvoiceId());
        final Product product = getProduct.execute(invoiceItemDto.getProductId());

        final InvoiceItem invoiceItem = InvoiceItem.builder()
                .invoice(invoice)
                .product(product)
                .quantity(invoiceItemDto.getQuantity())
                .amount(invoiceItemDto.getAmount())
                .build();

        invoiceItemRepository.save(invoiceItem);

        return InvoiceItemDto.builder()
                .id(invoiceItem.getId())
                .invoiceId(invoiceItem.getInvoice() != null ? invoiceItem.getInvoice().getId() : null)
                .productId(invoiceItem.getProduct() != null ? invoiceItem.getProduct().getId() : null)
                .quantity(invoiceItem.getQuantity())
                .amount(invoiceItem.getAmount())
                .build();
    }

    @Transactional
    public void updateInvoiceItem(UUID id, InvoiceItemDto invoiceItemDto) {
        Optional<InvoiceItem> invoiceItemData = invoiceItemRepository.findById(id);
        if (invoiceItemData.isPresent()) {

            final Invoice invoice = getInvoice.execute(invoiceItemDto.getInvoiceId());
            final Product product = getProduct.execute(invoiceItemDto.getProductId());

            final InvoiceItem invoiceItem = InvoiceItem.builder()
                .id(id)
                .invoice(invoice)
                .product(product)
                .amount(invoiceItemDto.getAmount())
                .quantity(invoiceItemDto.getQuantity())
                .build();
            invoiceItemRepository.save(invoiceItem);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found by provided id");
        }
    }

    public void deleteInvoiceItem(UUID id) {
        invoiceItemRepository.deleteById(id);
    }

}
