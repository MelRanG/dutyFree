package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import com.asianaidt.dutyfree.domain.purchase.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("/user/purchase")
    public String getUserPurchase(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        List<Purchase> purchaseList = purchaseService.getPurchaseList(member);
        model.addAttribute("purchaseList", purchaseList);

        return "";
    }

    @GetMapping("/user/purchase/{purchaseId}")
    public String getPurchaseDetail(HttpSession session,
                                    @PathVariable long purchaseId, Model model) {
        Member member = (Member) session.getAttribute("member");
        return "";
    }

}
