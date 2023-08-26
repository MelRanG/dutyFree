package com.asianaidt.dutyfree.domain.purchase.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class PurchaseDto {
    @JsonProperty("date")
    private LocalDateTime date;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("details")
    private List<PurchaseDetailDto> detailList;

}
