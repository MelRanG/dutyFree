package com.asianaidt.dutyfree.domain.stock.dto;

import com.asianaidt.dutyfree.domain.stock.domain.StockManager;
import com.asianaidt.dutyfree.domain.stock.domain.StockStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockManagerRequestDto {
    private Long stockId;
    private int quantity;
    private String memberId;

    public static StockManager toEntity(StockManagerRequestDto dto){
        return StockManager.builder()
                .status(StockStatus.PROGRESS)
                .memberId(dto.getMemberId())
                .quantity(dto.getQuantity())
                .build();
    }
}
