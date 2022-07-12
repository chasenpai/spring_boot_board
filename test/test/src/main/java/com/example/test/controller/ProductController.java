package com.example.test.controller;


import com.example.test.dto.ProductDto;
import com.example.test.dto.ProductResponseDto;
import com.example.test.dto.UpdateProductNameDto;
import com.example.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long id){
        ProductResponseDto productResponseDto = productService.getProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto){
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> updateProduct(
            @RequestBody UpdateProductNameDto updateProductNameDto) throws Exception{
        ProductResponseDto productResponseDto = productService.updateProduct(
                updateProductNameDto.getId(), updateProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(Long id) throws Exception{
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }


}
