package com.example.jpa.dao;

import com.example.jpa.entity.Product;
import com.example.jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao{

    private final ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);

        return saveProduct;
    }

    @Override
    public Product selectProduct(Long id) {
        Product selectProduct = productRepository.getById(id);

        return selectProduct;
    }

    @Override
    public Product updateProductName(Long id, String name) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(id);

        Product updateProduct;
        if(selectProduct.isPresent()){
            Product product = selectProduct.get();

            product.setName(name);
            product.setUpdateDate(LocalDateTime.now());

            updateProduct = productRepository.save(product);
        }else{
            throw new Exception();
        }

        return updateProduct;
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(id);

        if(selectProduct.isPresent()){
            Product product = selectProduct.get();

            productRepository.delete(product);
        }else{
            throw new Exception();
        }
    }
}
