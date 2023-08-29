package com.asianaidt.dutyfree.domain.product.dto;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductListDto {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String brand;
    private String path;
    private String changeMoney;

    @Builder
    public ProductListDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.path = product.getPath();
    }

}
