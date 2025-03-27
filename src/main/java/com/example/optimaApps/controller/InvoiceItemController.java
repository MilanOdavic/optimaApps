package com.example.optimaApps.controller;

import com.example.optimaApps.dto.InvoiceItemDto;
import com.example.optimaApps.service.InvoiceItemService;
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
@RequestMapping("/invoiceitem")
public class InvoiceItemController {

    @Autowired
    private InvoiceItemService invoiceItemService;

    @PostMapping("/createInvoiceItem")
    @Operation(description = "Create invoice item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItemDto createInvoiceItem(@RequestBody InvoiceItemDto invoiceItemDto) {
        return invoiceItemService.createInvoiceItem(invoiceItemDto);
    }

    @GetMapping("/getAllInvoiceItems")
    @Operation(description = "Get all invoice items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "404", description = "InvoiceItems not found"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItemDto> getAllInvoiceItems(@Parameter(description = "Invoice id") @RequestParam(name="id") UUID id) {
        return invoiceItemService.getAllInvoiceItems(id);
    }

    @GetMapping("/getInvoiceItemById")
    @Operation(description = "Get invoiceItem by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "404", description = "InvoiceItem not found"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItemDto getInvoiceItemById(@Parameter(description = "InvoiceItem id") @RequestParam(name="id") UUID id) {
        return invoiceItemService.getInvoiceItemById(id);
    }

    @PostMapping("/updateInvoiceItem")
    @Operation(description = "Update invoiceItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoiceItem(@Parameter(description = "InvoiceItem id") @RequestParam(name="id") UUID id,
                             @RequestBody InvoiceItemDto invoiceItem) {
        invoiceItemService.updateInvoiceItem(id, invoiceItem);
    }

    @PostMapping("/deleteInvoiceItem")
    @Operation(description = "Delete invoiceItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceItem(@Parameter(description = "InvoiceItem id") @RequestParam(name="id") UUID id) {
        invoiceItemService.deleteInvoiceItem(id);
    }
}
