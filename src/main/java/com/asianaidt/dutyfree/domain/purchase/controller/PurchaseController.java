package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.product.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.product.service.ProductService;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("list")
public class PurchaseController {
    private final ProductService productService;
    private final PurchaseService purchaseService;

    @GetMapping("/{categoryId}/product/{productId}")
    public String getPurchase() {
        return "";
    }

    @PostMapping("/{categoryId}/product/{productId}")
    public String purchaseOne(HttpSession session, @PathVariable Long categoryId, @PathVariable Long productId,
                           @RequestBody PurchaseDto purchaseDto) {
        Member member = (Member) session.getAttribute("user");
        purchaseService.purchaseOne(member, productId, purchaseDto.getQuantity());

        return "";
    }

}
