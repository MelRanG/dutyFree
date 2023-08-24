package com.asianaidt.dutyfree.domain.product.controller;

import com.asianaidt.dutyfree.domain.product.domain.ProductImg;
import com.asianaidt.dutyfree.domain.product.service.ProductImgService;
import com.asianaidt.dutyfree.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductImgService productImgService;

    private final ResourceLoader resourceLoader;

    @PostMapping("/add")
    public String addProduct() {
        productService.addProduct();
        return "test";
    }

    @GetMapping("/img")
    public String imgTest(Model model){
        String imgUrl = productImgService.getImage(10L);

        model.addAttribute("imgUrl", imgUrl);
        return "test";
    }

}
