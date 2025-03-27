package com.example.optimaApps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class InvoiceResponseDto {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private int invoiceNumber;

    @JsonProperty
    private LocalDateTime issueDate;

    @JsonProperty
    private UUID customerId;

    @JsonProperty
    private int totalAmount;

    @JsonProperty
    private List<InvoiceItemDto> invoiceItems;
}
