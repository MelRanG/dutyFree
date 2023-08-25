package com.asianaidt.dutyfree.domain.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
