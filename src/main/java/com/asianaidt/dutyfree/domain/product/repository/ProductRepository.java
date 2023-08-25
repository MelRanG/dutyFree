package com.asianaidt.dutyfree.domain.product.repository;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 리스트 전체 불러옴
//    List<Product> findByCategoryId(Long categoryId);

//    Page<Product> findByCategoryIdOrderByPriceAsc(Long categoryId);
//    Page<Product> findByCategoryIdOrderByPriceDesc(Long categoryId);
}
