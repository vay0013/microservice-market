package com.vay.orderservice.dto;

public record NewProductToOrder(
        Long id,
        String name,
        String description,
        double price
) {
}
