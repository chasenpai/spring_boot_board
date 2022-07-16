package com.example.jpa.repository;

import com.example.jpa.entity.Product;
import com.example.jpa.entity.Provider;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void providerTest(){
        Provider provider = new Provider();
        provider.setName("공급업체");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("연필");
        product.setPrice(1000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println("상품 정보 : " + productRepository.findById(product.getId()).orElseThrow(RuntimeException::new));
        System.out.println("공급업체 : "  + productRepository.findById(provider.getId()).orElseThrow(RuntimeException::new).getProvider());
    }

    @Test
    void mappingTest(){
        Provider provider = new Provider();
        provider.setName("공급업체1");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("지우개");
        product1.setPrice(1000);
        product1.setStock(20);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("샤프");
        product2.setPrice(2000);
        product2.setStock(50);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(10);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product product: productList){
            System.out.println(product);
        }
    }

    //persist
    @Test
    void cascadeTest1(){
        Provider provider = new Provider();
        provider.setName("모나미");

        Product product1 = new Product();
        product1.setName("지우개");
        product1.setPrice(1000);
        product1.setStock(20);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("샤프");
        product2.setPrice(2000);
        product2.setStock(50);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(10);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);

    }


    @Test
    @Transactional
    void orphanRemovalTest(){
        Provider provider = new Provider();
        provider.setName("MONAMI");

        Product product1 = new Product();
        product1.setName("지우개");
        product1.setPrice(1000);
        product1.setStock(20);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("샤프");
        product2.setPrice(2000);
        product2.setStock(50);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(10);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider findProvider = providerRepository.findById(1L).get();
        findProvider.getProductList().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }
}
