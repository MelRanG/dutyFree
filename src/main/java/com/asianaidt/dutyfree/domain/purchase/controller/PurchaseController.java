package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("list")
@Slf4j
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/{categoryId}/product/{productId}")
    public String getPurchase() {
        return "";
    }

    @PostMapping("/{categoryId}/product/{productId}")
    public String purchaseOne(HttpSession session, @PathVariable Long categoryId, @PathVariable Long productId,
                           @RequestParam int quantity) {

        Member member = (Member) session.getAttribute("user");
        purchaseService.purchase(member, productId, quantity);

        return "test";
    }

}
