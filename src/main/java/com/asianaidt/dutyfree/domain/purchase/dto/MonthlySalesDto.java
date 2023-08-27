package com.asianaidt.dutyfree.domain.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class MonthlySalesDto {
    private int year;
    private int month;
    private long price;
    private long totalSales;

}
