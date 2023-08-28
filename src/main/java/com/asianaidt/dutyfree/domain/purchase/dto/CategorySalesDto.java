package com.asianaidt.dutyfree.domain.purchase.dto;

import com.asianaidt.dutyfree.domain.product.domain.Category;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class CategorySalesDto {
    private final String category;
    private final Long totalSales;

    public CategorySalesDto(Category category, Long totalSales) {
        this.category = category.getName();
        this.totalSales = totalSales;
    }
}
