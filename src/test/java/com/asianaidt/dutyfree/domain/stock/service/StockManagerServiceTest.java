package com.asianaidt.dutyfree.domain.stock.service;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.dto.StockManagerRequestDto;
import com.asianaidt.dutyfree.domain.stock.repository.StockManagerRepository;
import com.asianaidt.dutyfree.domain.stock.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockManagerServiceTest {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StockManagerRepository stockManagerRepository;
    @Autowired
    StockManagerService stockManagerService;

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
    public void stockManagerInsert(){
        StockManagerRequestDto dto = StockManagerRequestDto.builder()
                .memberId("t")
                .quantity(2)
                .stockId(1L)
                .build();
//        Stock stock = stockManagerService.insertStock(dto);
//        assertEquals(stock.getQuantity(),52);
    }
}
