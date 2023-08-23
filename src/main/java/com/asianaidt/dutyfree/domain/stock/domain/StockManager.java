package com.asianaidt.dutyfree.domain.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class StockManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockManagerId")
    private Long id;
    @CreationTimestamp
    @Column(name = "regDate", nullable = false)
    private LocalDateTime regDate;
    private int quantity;
    private String userId;
    private String status;
    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stock;
}
