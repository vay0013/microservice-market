package com.vay.orderservice.dto;

public record ProductDto(
        Long id,
        String name,
        String description,
        double price
) {
}
