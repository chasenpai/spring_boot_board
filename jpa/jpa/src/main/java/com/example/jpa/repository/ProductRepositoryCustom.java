package com.example.jpa.repository;

import com.example.jpa.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByName(String name);
}
