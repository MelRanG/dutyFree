package com.asianaidt.dutyfree.domain.product.dto;

import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryListDto {
    private Long id;
    private String name;
    private Product product;

    @Builder
    public CategoryListDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
