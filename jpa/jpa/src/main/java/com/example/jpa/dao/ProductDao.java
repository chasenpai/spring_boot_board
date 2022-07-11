package com.example.jpa.dao;

import com.example.jpa.entity.Product;

public interface ProductDao {

    Product insertProduct(Product product);

    Product selectProduct(Long id);

    Product updateProductName(Long id, String name) throws Exception;

    void deleteProduct(Long id) throws  Exception;
}
