package com.asianaidt.dutyfree.domain.product.repository;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 리스트 전체 불러옴
//    List<Product> findByCategoryId(Long categoryId);

//    Page<Product> findByCategoryIdOrderByPriceAsc(Long categoryId);
//    Page<Product> findByCategoryIdOrderByPriceDesc(Long categoryId);

    Page<Product> findByCategoryIdOrderByPriceAsc(Long categoryId, Pageable pageable);
    Page<Product> findByCategoryIdOrderByPriceDesc(Long categoryId, Pageable pageable);

    Page<Product> findByCategoryIdAndPriceBetween(Long categoryId, Integer min, Integer max, Pageable pageable);

    Optional<Product> findById(Long productId);

    List<Product> findTop5ByOrderByIdDesc();

    List<Product> findTop4ByOrderByName();

    Page<Product> findAllByNameContaining(String productName, Pageable pageable);
}
