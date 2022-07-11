package com.example.jpa.service;

import com.example.jpa.dao.ProductDao;
import com.example.jpa.dto.ProductDto;
import com.example.jpa.dto.ProductResponseDto;
import com.example.jpa.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao){
        this.productDao = productDao;
    }


    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productDao.selectProduct(id);
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setRegDate(LocalDateTime.now());
        product.setUpdateDate(LocalDateTime.now());

        Product saveProduct = productDao.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(saveProduct.getId());
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, String name) throws Exception {
        Product updateProduct = productDao.updateProductName(id, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(updateProduct.getId());
        productResponseDto.setName(updateProduct.getName());
        productResponseDto.setPrice(updateProduct.getPrice());
        productResponseDto.setStock(updateProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        productDao.deleteProduct(id);
    }
}
