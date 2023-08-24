package com.asianaidt.dutyfree.domain.purchase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController {

    @GetMapping("/")
    public String getCart() {
        return "cart";
    }


}
