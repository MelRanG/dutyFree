package com.asianaidt.dutyfree.domain.purchase.dto;

import com.asianaidt.dutyfree.domain.purchase.dto.cart.DepartureDto;
import com.asianaidt.dutyfree.domain.purchase.dto.cart.PassportDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PurchaseDto {
    List<CartProductDto> products;
    PassportDto passportInfo;
    DepartureDto departureInfo;
}
