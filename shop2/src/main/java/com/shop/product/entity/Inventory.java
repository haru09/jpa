package com.shop.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Inventory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;
    private String stockType; // ex) 현재고, 안전재고 등
    private int quantity;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}