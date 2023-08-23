package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.service.ProductService;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PurchaseController {
    private final ProductService productService;
    private final PurchaseService purchaseService;

    @PostMapping("/purchase")
    public String purchase() {
        Product product = productService.addProduct();
        purchaseService.purchase(product);
        return "test";
    }
}
