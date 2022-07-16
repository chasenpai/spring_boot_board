package com.example.jpa.repository;

import com.example.jpa.entity.Product;
import com.example.jpa.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveAndReadTest1(){
        Product product = new Product();
        product.setName("테스트1");
        product.setPrice(100000);
        product.setStock(20);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDetail("상세 테스트1");

        productDetailRepository.save(productDetail);

        System.out.println("상품 조회: " + productDetailRepository.findById(productDetail.getId()).get().getProduct());
        System.out.println("상품 상세: " + productDetailRepository.findById(productDetail.getId()).get());
    }
}
