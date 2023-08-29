package com.asianaidt.dutyfree.domain.purchase.controller;

import com.asianaidt.dutyfree.domain.member.domain.Flight;
import com.asianaidt.dutyfree.domain.member.dto.MemberResponseDto;
import com.asianaidt.dutyfree.domain.product.dto.CategoryListDto;
import com.asianaidt.dutyfree.domain.product.service.CategoryService;
import com.asianaidt.dutyfree.domain.product.service.ProductService;
import com.asianaidt.dutyfree.domain.purchase.dto.CartProductDto;
import com.asianaidt.dutyfree.domain.purchase.dto.PurchaseDto;
import com.asianaidt.dutyfree.domain.purchase.dto.cart.DepartureDto;
import com.asianaidt.dutyfree.domain.purchase.dto.cart.PassportDto;
import com.asianaidt.dutyfree.domain.purchase.service.CartService;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class CartController {
    private final PurchaseService purchaseService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CartService cartService;
    @GetMapping("/cart")
    public String getCart(HttpSession session) {
        log.info("session cart = {}", session);
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
    public ResponseEntity<String> purchase(HttpSession session, @RequestBody PurchaseDto purchaseDto) throws InterruptedException {
        PassportDto passportInfo = purchaseDto.getPassportInfo();
        DepartureDto departureDto = purchaseDto.getDepartureInfo();
        MemberResponseDto memberDto = (MemberResponseDto) session.getAttribute("user");

        List<CartProductDto> detailDtoList = purchaseDto.getProducts();

        cartService.addDepartureInfo(memberDto, departureDto);
        cartService.addPassportInfo(memberDto, passportInfo);
        purchaseService.purchaseMany(memberDto, detailDtoList);

        return ResponseEntity.ok().body("SUCCESS");
    }
}
