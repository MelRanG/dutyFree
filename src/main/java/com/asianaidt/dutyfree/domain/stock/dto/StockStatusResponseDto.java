package com.asianaidt.dutyfree.domain.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StockStatusResponseDto {
    private Long stockManagerId;
    private String name;
    private String brand;
    private int price;
    private String category;
    private int purchaseCount;
    private String managerId;
}
