package com.asianaidt.dutyfree.domain.stock.service;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.facade.OptimisticLockStockFacade;
import com.asianaidt.dutyfree.domain.stock.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockServiceTest {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OptimisticLockStockFacade stockService;

    Product product;
    Stock stock;
    @BeforeEach
    public void before(){
        product = Product.builder()
                .name("123")
                .brand("구찌")
                .price(1000)
                .category(null)
                .build();
        productRepository.save(product);

        stock = Stock.builder()
                .quantity(50)
                .product(product)
                .build();
        stockRepository.save(stock);
    }

    @Test
    public void stockInsert(){

        Stock stock = Stock.builder()
                .quantity(50)
                .product(product)
                .build();
        stockRepository.save(stock);

        Stock s = stockRepository.findById(stock.getId()).orElseThrow();
        Assertions.assertEquals(s.getId(), stock.getId());
    }

    @Test
    public void 수량감소() throws InterruptedException {
        stockService.decrease(stock.getId(), 5);
        stockRepository.findById(stock.getId());
        assertEquals(45,stockRepository.findById(stock.getId()).get().getQuantity());
    }

    @Test
    public void 동시에_40명이_주문() throws InterruptedException {
        int threadCount = 40;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decrease(1L, 1);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();

        // 50 - (40 * 1) = 0
        assertEquals(10, stock.getQuantity());
    }

}