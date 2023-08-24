package com.asianaidt.dutyfree.domain.purchase.service;


import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseDetail;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseLog;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseDetailRepository;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseRepository;
import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final StockRepository stockRepository;
    public void purchase(Member member, Long productId, int quantity) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<Stock> stock = stockRepository.findByProductId(productId);

        if(product.isPresent() && stock.isPresent()) {
            if(stock.get().getQuantity() < quantity) {
                throw new RuntimeException("재고가 없습니다.");
            }

            // 수량 감소

            Purchase purchase = Purchase.builder()
                    .regDate(LocalDateTime.now())
                    .totalAmount(quantity)
                    .member(member)
                    .build();

            purchaseRepository.save(purchase);

            PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                    .purchase(purchase)
                    .quantity(quantity)
                    .product(product.get())
                    .build();

            purchaseDetailRepository.save(purchaseDetail);

            PurchaseLog purchaseLog = PurchaseLog.builder()
                    .regDate(LocalDateTime.now())

                    .build();

        }
    }
}
