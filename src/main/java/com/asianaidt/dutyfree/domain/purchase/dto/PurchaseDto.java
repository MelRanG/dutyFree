package com.asianaidt.dutyfree.domain.purchase.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PurchaseDto {
    @JsonProperty("date")
    private LocalDateTime date;
    @JsonProperty("price")
    private int price;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("details")
    private List<PurchaseDetailDto> detailList;

}
