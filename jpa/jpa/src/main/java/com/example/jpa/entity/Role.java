package com.example.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<>();

}
