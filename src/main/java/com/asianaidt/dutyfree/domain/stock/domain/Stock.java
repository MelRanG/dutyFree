package com.asianaidt.dutyfree.domain.stock.domain;

import com.asianaidt.dutyfree.domain.product.domain.Product;
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
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockId")
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    @OneToMany(mappedBy = "stock")
    private List<StockManager> stockManagers;
}
