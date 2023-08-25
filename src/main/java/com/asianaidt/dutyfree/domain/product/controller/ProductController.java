package com.asianaidt.dutyfree.domain.product.controller;

import com.asianaidt.dutyfree.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
//    private final ProductImgService productImgService;
    private final ResourceLoader resourceLoader;

    @GetMapping("/")
    public String main(){
        return "Main";
    }

//    @GetMapping("/img")
//    public String imgTest(Model model){
//        String imgUrl = productImgService.getImage(10L);
//
//        model.addAttribute("imgUrl", imgUrl);
//        return "test";
//    }

    // 카테고리 별 상품 리스트 => 카테고리 아이디가 같은 상품 리스트 전체 반환
    // @PathVariable 으로 카테고리 아이디 필요
    // 오름차 내림차를 통해 변경 가능해서 삭제
//    @GetMapping("/list/{categoryId}")
//    public String productListByCategoryId(
//            Model model, @PathVariable Long categoryId, Pageable pageable) {
//
//        return null;
//    }


    // 상품 카테고리 별 가격 검색 => 필터링 된 상품 리스트 반환
    // 쿼리스트링 이라 @RequestParam 으로 price 필요
    // @PathVariable 으로 max, min 값 필요
    // @RequestParam(value = "no", required = false) int no
    // 삼품 가격 별 정렬 => 낮은 가격, 높은 가격 정렬
    // /list/{categoryId}/price?sort={sort}
    @GetMapping("/list/{categoryId}")
    public String sortByPrice(
            Model model, @PathVariable Long categoryId,
            @RequestParam(defaultValue = "Asc") String sort,
            Pageable pageable
    ) {
        productService.getProductByCategoryId(categoryId, sort, pageable);
//        productImgService.getProductImgByCategoryId();
        return null;
    }

    // ? = 리퀘스트 파람

    @GetMapping("/list/{categoryId}/price")
    public String productListByCategoryIdAndPrice(
            Model model, @PathVariable Long categoryId,
            @RequestParam Integer min, @RequestParam Integer max, Pageable Pageable
    ) {
        return null;
    }
    // 상품 페이지 상세 => 상품 정보(디스크립션) 반환
    // /list/{categoryId}/product/{productId}
    @GetMapping("/product/{productId}")
    public String productDetail(
            Model model, @PathVariable Long productId
    ) {
        return null;
    }
    // 상품 검색 => 검색한 내용이 포함된 모든 상품 리스트
    @GetMapping("/search/{productName}")
    public String searchProduct(
            Model model, @PathVariable String productName
    ) {
        return null;
    }
}
