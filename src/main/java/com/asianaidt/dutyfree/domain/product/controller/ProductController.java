package com.asianaidt.dutyfree.domain.product.controller;

import com.asianaidt.dutyfree.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public String addProduct() {
        productService.addProduct();
        return "test";
    }

}
