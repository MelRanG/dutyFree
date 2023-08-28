package com.asianaidt.dutyfree.domain.stock.dto;

import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.domain.StockManager;
import com.asianaidt.dutyfree.domain.stock.domain.StockStatus;
import com.asianaidt.dutyfree.domain.stock.repository.StockRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Builder
@ToString
public class StockManagerRequestDto {
    private Long stockId;
    private int quantity;
    private String memberId;


    public static StockManager toEntity(StockManagerRequestDto dto, Stock stock){
        return StockManager.builder()
                .status(StockStatus.PROGRESS)
                .memberId(dto.getMemberId())
                .quantity(dto.getQuantity())
                .stock(stock)
                .build();
    }
}
