package com.vay.catalogueservice.service;

import com.vay.catalogueservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts(String filter);
    Optional<Product> getProduct(long productId);
    Product createProduct(Product product);
    void updateProduct(long productId, String name,
                       String description, double price);
    void deleteProduct(long productId);
}
