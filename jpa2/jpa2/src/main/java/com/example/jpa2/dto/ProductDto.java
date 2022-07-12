package com.example.jpa2.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class ProductDto {

    private String name;
    private int price;
    private int stock;

}
