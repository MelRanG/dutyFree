package com.asianaidt.dutyfree.domain.purchase.service;


import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseDetail;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseDetailRepository;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    public void purchase(Product product) {
        Purchase purchase = Purchase.builder()
                .build();
        Purchase p = purchaseRepository.save(purchase);
        PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                .quantity(4)
                .product(product)
                .purchase(p)
                .build();


        PurchaseDetail purchaseDetail2 = PurchaseDetail.builder()
                .quantity(5)
                .product(product)
                .purchase(p)
                .build();
        purchaseDetailRepository.save(purchaseDetail);
        purchaseDetailRepository.save(purchaseDetail2);
    }
}
