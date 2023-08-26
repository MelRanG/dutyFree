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
    @Version
    private Long version;

    public void decrease(int quantity) {
        if (this.quantity - quantity < 0) {
            throw new RuntimeException("수량은 0이하가 될 수 없습니다.");
        }
        this.quantity -= quantity;
    }

    public void updateQuantity(int quantity){
        this.quantity += quantity;
    }
}
