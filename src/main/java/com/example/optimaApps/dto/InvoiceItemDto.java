package com.example.optimaApps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class InvoiceItemDto {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private UUID invoiceId;

    @JsonProperty
    private UUID productId;

    @JsonProperty
    private int quantity;

    @JsonProperty
    private int amount;
}
