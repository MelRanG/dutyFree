package com.asianaidt.dutyfree.domain.stock.facade;

import com.asianaidt.dutyfree.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptimisticLockStockFacade {
    private final StockService stockService;


    public void decrease(Long id, int quentity) throws InterruptedException{
        while (true){
            try{
                stockService.decrease(id, quentity);
                break;
            }catch (Exception e){
                Thread.sleep(150);
            }
        }
    }
}
