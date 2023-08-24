package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.product.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final PurchaseService purchaseService;
    @GetMapping("/cart")
    public String getCart() {
        return "cart";
    }

    @PostMapping("/purchase")
    public String purchase(@RequestBody PurchaseDto purchaseDto) {

        return "";
    }

}
