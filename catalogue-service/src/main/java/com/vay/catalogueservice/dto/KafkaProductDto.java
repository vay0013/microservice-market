package com.vay.catalogueservice.dto;

import java.io.Serializable;
import java.util.UUID;

public record KafkaProductDto(UUID userId, long productId) implements Serializable {
}
