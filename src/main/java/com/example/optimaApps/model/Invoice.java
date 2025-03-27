package com.example.optimaApps.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "invoice_number")
    private int invoiceNumber;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "total_amount")
    private int totalAmount;

    @Builder.Default
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private Set<InvoiceItem> invoiceItems = new HashSet<>();

}
