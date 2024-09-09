package com.vay.catalogueservice.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.vay.catalogueservice.model.Product}
 */
public record ProductDto(String name, String description, Double price) implements Serializable {
}