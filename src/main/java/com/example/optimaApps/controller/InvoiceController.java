package com.example.optimaApps.controller;

import com.example.optimaApps.dto.InvoiceDto;
import com.example.optimaApps.dto.InvoiceResponseDto;
import com.example.optimaApps.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/createInvoice")
    @Operation(description = "Create invoice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.createInvoice(invoiceDto);
    }

    @GetMapping("/getAllInvoices")
    @Operation(description = "Get all invoices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "404", description = "Invoices not found"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceResponseDto> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/getInvoiceById")
    @Operation(description = "Get invoice by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "404", description = "Invoice not found"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public InvoiceResponseDto getInvoiceById(@Parameter(description = "Invoice id") @RequestParam(name="id") UUID id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping("/updateInvoice")
    @Operation(description = "Update invoice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoice(@Parameter(description = "Invoice id") @RequestParam(name="id") UUID id,
                             @RequestBody InvoiceDto invoice) {
        invoiceService.updateInvoice(id, invoice);
    }

    @PostMapping("/deleteInvoice")
    @Operation(description = "delete invoice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@Parameter(description = "Invoice id") @RequestParam(name="id") UUID id) {
        invoiceService.deleteInvoice(id);
    }
}
