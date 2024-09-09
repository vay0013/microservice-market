package com.vay.catalogueservice.controller;

import com.vay.catalogueservice.dto.ProductDto;
import com.vay.catalogueservice.model.Product;
import com.vay.catalogueservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products/{productId:\\d+}")
public class ProductRestController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product getProduct(@PathVariable("productId") long productId) {
        return productService.getProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("Product with id: %d not found".formatted(productId)));
    }

    @GetMapping
    public ResponseEntity<Product> getProductById(@ModelAttribute("productId") Product product) {
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId,
                                                 @RequestBody ProductDto productDto) {
        productService.updateProduct(
                productId,
                productDto.name(),
                productDto.description(),
                productDto.price());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
