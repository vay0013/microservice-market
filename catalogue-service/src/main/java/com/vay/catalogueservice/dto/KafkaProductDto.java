package com.vay.catalogueservice.dto;

import java.io.Serializable;

public record KafkaProductDto(Long id, String name, String description, double price) implements Serializable {
}
