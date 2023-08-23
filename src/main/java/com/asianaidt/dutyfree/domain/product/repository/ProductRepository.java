package com.asianaidt.dutyfree.domain.product.repository;

import com.asianaidt.dutyfree.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
