package com.asianaidt.dutyfree.domain.product.dto;

import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.domain.StockManager;
import com.asianaidt.dutyfree.domain.stock.domain.StockStatus;
import com.asianaidt.dutyfree.domain.stock.dto.StockManagerRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryDto {
    private Long id;
    private String name;
//    @Builder
    public CategoryDto toEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Builder
    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
