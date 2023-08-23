package com.asianaidt.dutyfree.domain.purchase.domain;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseDetailId")
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "purchaseId")
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
