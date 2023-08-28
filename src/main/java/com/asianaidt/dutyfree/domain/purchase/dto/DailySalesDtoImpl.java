package com.asianaidt.dutyfree.domain.purchase.dto;

import java.time.LocalDate;

public class DailySalesDtoImpl implements DailySalesDto{
    private LocalDate day;
    private Integer totalSales;
    public DailySalesDtoImpl(LocalDate day, Integer totalSales) {
        this.day = day;
        this.totalSales = totalSales;
    }

    @Override
    public LocalDate getDay() {
        return day;
    }

    @Override
    public Integer getTotalSales() {
        return totalSales;
    }
}
