package com.example.test.service;

import com.example.test.dto.ProductDto;
import com.example.test.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long id);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto updateProduct(Long id, String name) throws Exception;

    void deleteProduct(Long id) throws Exception;
}
