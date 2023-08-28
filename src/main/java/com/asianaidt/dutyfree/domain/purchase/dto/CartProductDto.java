package com.asianaidt.dutyfree.domain.purchase.dto;

import com.asianaidt.dutyfree.domain.product.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CartProductDto {
    private Long id;
    private String path;
    private String name;
    private String brand;
    private int price;
    private int quantity;

    @Builder
    public CartProductDto(ProductDto product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.brand = product.getBrand();
        this.path = product.getPath();
    }

}
