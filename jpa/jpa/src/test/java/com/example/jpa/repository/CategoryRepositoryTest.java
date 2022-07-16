package com.example.jpa.repository;

import com.example.jpa.entity.Category;
import com.example.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void categoryTest(){
        Category category = new Category();
        category.setCode("O1");
        category.setName("문구");

        categoryRepository.save(category);

        Product product1 = new Product();
        product1.setName("연필");
        product1.setPrice(500);
        product1.setStock(100);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setName("지우개");
        product2.setPrice(500);
        product2.setStock(100);
        product2.setCategory(category);

        Product product3 = new Product();
        product3.setName("도화지");
        product3.setPrice(500);
        product3.setStock(100);
        product3.setCategory(category);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = categoryRepository.findById(category.getId()).get().getProductList();

        for(Product products : productList){
            System.out.println(products);
        }

    }
}
