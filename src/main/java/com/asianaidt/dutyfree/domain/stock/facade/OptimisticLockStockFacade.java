package com.asianaidt.dutyfree.domain.stock.facade;

import com.asianaidt.dutyfree.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OptimisticLockStockFacade {
    private final StockService stockService;


    public void decrease(Long id, int quantity) throws InterruptedException{
        int count = 0;
        while (count < 50){
            try{
                stockService.decrease(id, quantity);
                break;
            }catch (Exception e){
                log.error(e.getMessage());
                Thread.sleep(150);
            }finally {
                count++;
            }
        }
    }
}
