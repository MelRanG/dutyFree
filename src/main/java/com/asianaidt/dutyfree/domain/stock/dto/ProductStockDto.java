package com.asianaidt.dutyfree.domain.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class ProductStockDto {
    private String name;
    private String brand;
    private String category;
    private int price;
    private int quantity;
}
