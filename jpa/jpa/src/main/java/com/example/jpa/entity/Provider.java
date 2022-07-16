package com.example.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provider")
@Getter @Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public class Provider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();
}
