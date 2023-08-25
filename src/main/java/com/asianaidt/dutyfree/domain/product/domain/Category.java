package com.asianaidt.dutyfree.domain.product.domain;

import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    List<PurchaseLog> purchaseLogs = new ArrayList<>();
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
