package com.asianaidt.dutyfree.domain.purchase.service;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseDetail;
import com.asianaidt.dutyfree.domain.purchase.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseRepository;
import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.service.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PurchaseServiceTest {

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    StockService stockService;

    Member member;
    Product product;
    Product product2;
    Stock stock;

    @BeforeEach
    void init() {
        member = Member.builder()
                .id("member1")
                .password("1234")
                .build();

        product = Product.builder()
                .id((long) 1)
                .name("블랙 로고패턴 가죽 클러치백")
                .brand("닥스")
                .price(133)
                .build();

        stock = Stock.builder()
                .quantity(50)
                .product(product)
                .build();
    }

    @Test
    @DisplayName("구매 하기")
    void purchase() {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setQuantity(10);

        purchaseService.purchase(member, product.getId(), purchaseDto);

    }

    @Test
    @DisplayName("구매 목록 조회")
    void getPurchaseList() {
        List<Purchase> purchaseList = purchaseService.getPurchaseList(member.getId());
        for(Purchase p : purchaseList) {
            Assertions.assertEquals(p.getMember().getId(), member.getId());

            for (PurchaseDetail detail: p.getPurchaseDetails()) {
                Assertions.assertEquals(detail.getQuantity(), 10);
                Assertions.assertEquals(detail.getProduct().getId(), 1);
            }
        }
    }

    @Test
    @DisplayName("구매 단일 조회")
    void getPurchase() {
        List<PurchaseDetail> details = purchaseService.getPurchase((long) 16);
        for (PurchaseDetail detail : details) {
            Assertions.assertEquals(detail.getProduct().getId(), product.getId());
        }
    }
}