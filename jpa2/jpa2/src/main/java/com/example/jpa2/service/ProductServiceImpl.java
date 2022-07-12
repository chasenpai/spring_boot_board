package com.example.jpa2.service;

import com.example.jpa2.dto.ProductDto;
import com.example.jpa2.dto.ProductResponseDto;
import com.example.jpa2.entity.Product;
import com.example.jpa2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){

        this.productRepository = productRepository;
    }


    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id).get();
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

        Product saveProduct = productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(saveProduct.getId());
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, String name) throws Exception {
        Product findProduct = productRepository.findById(id).get();
        findProduct.setName(name);
        Product updateProduct = productRepository.save(findProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(updateProduct.getId());
        productResponseDto.setName(updateProduct.getName());
        productResponseDto.setPrice(updateProduct.getPrice());
        productResponseDto.setStock(updateProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        productRepository.deleteById(id);
    }
}
