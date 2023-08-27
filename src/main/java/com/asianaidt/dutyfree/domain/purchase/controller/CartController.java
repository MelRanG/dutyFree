package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.product.dto.CategoryListDto;
import com.asianaidt.dutyfree.domain.product.service.CategoryService;
import com.asianaidt.dutyfree.domain.purchase.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final PurchaseService purchaseService;
    private final CategoryService categoryService;
    @GetMapping("/cart")
    public String getCart(HttpSession session, Model model) {
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);

//        ProductDto product1 = productService.getProductDetail(1L);
//        ProductDto product2 = productService.getProductDetail(2L);
//
//        CartProductDto d1 = CartProductDto.builder()
//                .name(product1.getName())
//                .path(product1.getPath())
//                .price(product1.getPrice())
//                .quantity(2)
//                .build();
//
//        CartProductDto d2 = CartProductDto.builder()
//                .name(product2.getName())
//                .path(product2.getPath())
//                .price(product2.getPrice())
//                .quantity(3)
//                .build();
//
//        List<CartProductDto> list = new ArrayList<>();
//        list.add(d1);
//        list.add(d2);
//
//        model.addAttribute("products", list);
        return "Cart";
    }

    @GetMapping("/passport")
    public String passport(HttpSession session, Model model) {
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);

        return "Passport";
    }

    @GetMapping("/departure")
    public String departure(HttpSession session, Model model) {
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);

        return "Departure";
    }

    @GetMapping("/purchase")
    public String purchasePage(Model model) {
        return "Purchase";
    }

    @PostMapping("/purchase")
    public String purchase(HttpSession session, @RequestBody PurchaseDto purchaseDto) throws InterruptedException {
        Member member = (Member) session.getAttribute("member");
        purchaseService.purchaseMany(member, purchaseDto);
        return "purchase";
    }
}
