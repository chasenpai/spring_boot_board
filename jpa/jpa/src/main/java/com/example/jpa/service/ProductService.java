package com.example.jpa.service;

import com.example.jpa.dto.ProductDto;
import com.example.jpa.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long id);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto updateProduct(Long id, String name) throws Exception;

    void deleteProduct(Long id) throws Exception;
}
