package com.example.jpa2.service;

import com.example.jpa2.dto.ProductDto;
import com.example.jpa2.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long id);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto updateProduct(Long id, String name) throws Exception;

    void deleteProduct(Long id) throws Exception;
}
