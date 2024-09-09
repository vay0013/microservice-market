package com.vay.orderservice.service;

import com.vay.orderservice.dto.NewProductToOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaProductService {
    @KafkaListener(topics = "topic", containerFactory = "productDtoListenerFactory")
    public void consumeProductDto(NewProductToOrder newProductToOrder) {
        
    }
}
