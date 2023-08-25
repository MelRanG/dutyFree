package com.asianaidt.dutyfree.domain.stock.service;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.domain.StockManager;
import com.asianaidt.dutyfree.domain.stock.domain.StockStatus;
import com.asianaidt.dutyfree.domain.stock.repository.StockManagerRepository;
import com.asianaidt.dutyfree.domain.stock.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StockManagerServiceTest {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StockManagerRepository stockManagerRepository;

    Product product;
    Stock stock;

    @BeforeEach
    public void befre(){
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
    public void stockManagerInsert(){
        StockManager stockManager = StockManager.builder()
                .stock(stock)
                .status(StockStatus.PROGRESS)
                .quantity(5)
                .build();
        stockManagerRepository.save(stockManager);

        StockStatus s = stockManagerRepository.findById(stockManager.getId()).orElseThrow().getStatus();
        assertEquals(s,StockStatus.PROGRESS);
    }
}
