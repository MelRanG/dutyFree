//package com.asianaidt.dutyfree.domain.product.service;
//
//import com.asianaidt.dutyfree.domain.product.domain.ProductImg;
//import com.asianaidt.dutyfree.domain.product.repository.ProductImgRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ProductImgService {
//    private final ProductImgRepository productImgRepository;
//    public String getImage(Long number) {
//        Optional<ProductImg> res = productImgRepository.findById(number);
//        ProductImg productImg = res.get();
//        return productImg.getPath();
//    }
//}
