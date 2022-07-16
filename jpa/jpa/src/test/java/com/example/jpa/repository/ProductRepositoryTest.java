package com.example.jpa.repository;

import com.example.jpa.entity.Product;
import com.example.jpa.entity.Provider;
import com.example.jpa.entity.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest(){

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setRegDate(LocalDateTime.now());
        product1.setUpdateDate(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setRegDate(LocalDateTime.now());
        product2.setUpdateDate(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setRegDate(LocalDateTime.now());
        product3.setUpdateDate(LocalDateTime.now());

        Product saveProduct1 = productRepository.save(product1);
        Product saveProduct2 = productRepository.save(product2);
        Product saveProduct3 = productRepository.save(product3);

        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"))));
        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"), Sort.Order.desc("stock"))));

    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void queryDslTest(){
        JPAQuery<Product> jpaQuery = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQuery
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.desc())
                .fetch();

        for(Product product : productList){
            System.out.println("--------------");
            System.out.println();
            System.out.println("상품 번호: " + product.getId());
            System.out.println("상품 이름: " + product.getName());
            System.out.println("상품 가격: " + product.getPrice());
            System.out.println("상품 재고: " + product.getStock());
            System.out.println();
            System.out.println("--------------");
        }
    }


    @Test
    void queryDslTest2(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = queryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();
    }


    @Test
    void queryDslTest3(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = queryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        List<Tuple> tupleList = queryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();
    }


    @Test
    public void auditingTest(){
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(50);

        Product saveProduct = productRepository.save(product);

        System.out.println("상품 명: " + saveProduct.getName());
        System.out.println("등록일자: " + saveProduct.getRegDate());
    }







}