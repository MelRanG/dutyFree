package com.asianaidt.dutyfree.domain.stock.service;

import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.dto.ProductStockDto;
import com.asianaidt.dutyfree.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long id, int quantity) {

        Stock stock = stockRepository.findByIdWithOptimisticLock(id);
        stock.decrease(quantity);
        Stock s = stockRepository.save(stock);
    }

    public Page<ProductStockDto> getProductStockList(Pageable pageable) {
        return stockRepository.findByProductStock(pageable);

    }
}
