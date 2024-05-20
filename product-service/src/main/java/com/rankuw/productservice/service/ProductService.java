package com.rankuw.productservice.service;

import com.rankuw.productservice.dto.ProductRequestDto;
import com.rankuw.productservice.dto.ProductResponseDto;
import com.rankuw.productservice.model.Product;
import com.rankuw.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDto productRequestDto) {
        log.error("OO bhaiiii");
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .description(productRequestDto.getDescription())
                .build();

        productRepository.save(product);
        log.info("Product created with id {}", product.getId());
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponseDto mapToProductResponse(Product product) {
        return ProductResponseDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .id(product.getId())
                .build();
    }
}
