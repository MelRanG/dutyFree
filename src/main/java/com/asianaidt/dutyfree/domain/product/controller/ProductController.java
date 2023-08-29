package com.asianaidt.dutyfree.domain.product.controller;

import com.asianaidt.dutyfree.domain.product.dto.CategoryListDto;
import com.asianaidt.dutyfree.domain.product.dto.ProductDto;
import com.asianaidt.dutyfree.domain.product.dto.ProductListDto;
import com.asianaidt.dutyfree.domain.product.service.CategoryService;
import com.asianaidt.dutyfree.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
//    private final ProductImgService productImgService;
    private final ResourceLoader resourceLoader;

    @GetMapping("/")
    public String main(Model model){

        List<ProductDto> recentProducts = productService.getRecentProduct();
        List<ProductDto> saleProducts = productService.getSaleProducts();
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        Integer res = productService.getUsd();

        model.addAttribute("products", recentProducts);
        model.addAttribute("saleProducts", saleProducts);
        model.addAttribute("category", categoryList);
        model.addAttribute("dollar", res);

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

    /*
        카테고리별 상품의 전체 리스트를 리턴하는 API
        sort = 오름차, 내림차
        categoryId = 1,2,3,4 => 각각 bag, beauty, electronic, perfume 을 의미함
     */
    @GetMapping("/list/{categoryId}")
    public String sortByPrice(
//    public ResponseEntity<?> sortByPrice(
            Model model, @PathVariable Long categoryId,
            @RequestParam(defaultValue = "Asc") String sort,
            Pageable pageable
    ) {
        Page<ProductListDto> sorting = productService.getProductByCategoryId(categoryId, sort, pageable);
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);
        model.addAttribute("sorting", sorting);
        Integer res = productService.getUsd();
        model.addAttribute("dollar", res);

        return "productList";
//        return ResponseEntity.ok(page);
    }

    // ? = 리퀘스트 파람

    /*
        가격대별 상품 정보를 리턴하는 API
        categoryId = 1,2,3,4 => 각각 bag, beauty, electronic, perfume 을 의미함
        min = 최저값
        max = 최대값

     */
    @GetMapping("/list/{categoryId}/price")
    public String productListByCategoryIdAndPrice(
//    public ResponseEntity<?> productListByCategoryIdAndPrice(
            Model model, @PathVariable Long categoryId,
            @RequestParam Integer min, @RequestParam Integer max, Pageable pageable
    ) {
        Page<ProductListDto> price = productService.getProductByPrice(categoryId, min, max, pageable);
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);
        model.addAttribute("price", price);
//        return ResponseEntity.ok(productByPrice);
        return null;
    }

    // 상품 페이지 상세 => 상품 정보(디스크립션) 반환
    // /list/{categoryId}/product/{productId}
    @GetMapping("/product/{productId}")
    public String productDetail(
//    public ResponseEntity<?> productDetail(
            Model model, @PathVariable Long productId
    ) {
        ProductDto detail = productService.getProductDetail(productId);
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);
        model.addAttribute("product", detail);
//        return ResponseEntity.ok(productDetail);
        return "ProductDetail";
    }
    // 상품 검색 => 검색한 내용이 포함된 모든 상품 리스트
    @GetMapping("/search/{productName}")
    public String searchProduct(
//    public ResponseEntity<?> searchProduct(
            Model model, @PathVariable String productName,
            Pageable pageable

    ) {
        Page<ProductListDto> search = productService.searchProduct(productName, pageable);
        List<CategoryListDto> categoryList = categoryService.getAllCategory();
        model.addAttribute("category", categoryList);
        model.addAttribute("search", search);
        return null;
//        return ResponseEntity.ok(productList);
    }
}
