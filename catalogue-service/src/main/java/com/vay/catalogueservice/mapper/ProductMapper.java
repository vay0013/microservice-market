package com.vay.catalogueservice.mapper;

import com.vay.catalogueservice.dto.KafkaProductDto;
import com.vay.catalogueservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    KafkaProductDto toDto(Product product);
}