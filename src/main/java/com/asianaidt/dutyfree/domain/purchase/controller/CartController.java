package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.purchase.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final PurchaseService purchaseService;
    @GetMapping("/cart")
    public String getCart() {
        return "cart";
    }

    @PostMapping("/purchase")
    public String purchase(HttpSession session, @RequestBody PurchaseDto purchaseDto) {
        Member member = (Member) session.getAttribute("member");
        purchaseService.purchaseMany(member, purchaseDto);
        return "purchase";
    }
}
