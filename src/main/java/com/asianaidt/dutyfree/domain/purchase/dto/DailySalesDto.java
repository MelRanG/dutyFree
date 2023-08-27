package com.asianaidt.dutyfree.domain.purchase.dto;

import java.time.LocalDate;

public interface DailySalesDto {
    LocalDate getDay();
    Integer getTotalSales();
}
