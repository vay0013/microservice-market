package com.vay.catalogueservice.service;

import com.vay.catalogueservice.model.Product;
import com.vay.catalogueservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(String filter) {
        if (filter != null || !filter.isBlank()) {
            return productRepository.findAllByNameLikeIgnoreCase("%" + filter + "%");
        }
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(long productId, String name,
                              String description, double price) {
        productRepository.findById(productId).ifPresentOrElse(
                product -> {
                    product.setName(name);
                    product.setDescription(description);
                    product.setPrice(price);
                }, () -> {
                    throw new NoSuchElementException();
                }
        );
    }

    @Override
    @Transactional
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }
}
