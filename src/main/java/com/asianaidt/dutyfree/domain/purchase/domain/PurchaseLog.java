package com.asianaidt.dutyfree.domain.purchase.domain;

import com.asianaidt.dutyfree.domain.product.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productLogId;
    @Column(name = "date")
    private LocalDateTime regDate;
    private int price;
    private int quantity;
    private String brand;
    private Long productId;
    private String productName;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
