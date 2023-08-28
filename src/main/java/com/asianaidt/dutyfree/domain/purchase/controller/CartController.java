package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.member.domain.Flight;
import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.product.dto.CategoryListDto;
import com.asianaidt.dutyfree.domain.product.dto.ProductDto;
import com.asianaidt.dutyfree.domain.product.service.CategoryService;
import com.asianaidt.dutyfree.domain.product.service.ProductService;
import com.asianaidt.dutyfree.domain.purchase.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.purchase.dto.cart.CartProductDto;
import com.asianaidt.dutyfree.domain.purchase.service.CartService;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final PurchaseService purchaseService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CartService cartService;
    @GetMapping("/cart")
    public String getCart(HttpSession session, Model model) {
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);

        List<CartProductDto> productDtoList = new ArrayList<>();
        ProductDto product = productService.getProductDetail(1L);
        CartProductDto product1 = new CartProductDto(product);
        product1.setQuantity(2);
        productDtoList.add(product1);

        model.addAttribute("products", productDtoList);

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

    @GetMapping("/flight")
    public ResponseEntity<List<Flight>> getFlightList(@RequestParam String flightDate,
                                        @RequestParam String flightCode, @RequestParam String boarding) {
        List<Flight> flightList = cartService.getFlightList(flightCode, flightDate, boarding);
        return ResponseEntity.ok().body(flightList);
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
