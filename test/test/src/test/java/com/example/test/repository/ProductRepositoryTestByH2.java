package com.example.test.repository;

import com.example.test.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTestByH2 {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest(){
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product saveProduct = productRepository.save(product);

        assertEquals(product.getName(), saveProduct.getName());
        assertEquals(product.getPrice(), saveProduct.getPrice());
        assertEquals(product.getStock(), saveProduct.getStock());

    }


    @Test
    void selectTest(){
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product saveProduct = productRepository.saveAndFlush(product);

        Product findProduct = productRepository.findById(saveProduct.getId()).get();

        assertEquals(product.getName(), saveProduct.getName());
        assertEquals(product.getPrice(), saveProduct.getPrice());
        assertEquals(product.getStock(), saveProduct.getStock());
    }

}
