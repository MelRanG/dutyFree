package com.asianaidt.dutyfree.domain.product.dto;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PurchaseDto {
    private LocalDateTime date;
    private int price;
    private int quantity;
    private String brand;
    private List<PurchaseDetailDto> purchaseList;
}
