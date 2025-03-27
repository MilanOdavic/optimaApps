package com.example.optimaApps.service;

import com.example.optimaApps.model.Product;
import com.example.optimaApps.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetProduct {

    private final ProductRepository productRepository;

    public GetProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product execute(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }
}
