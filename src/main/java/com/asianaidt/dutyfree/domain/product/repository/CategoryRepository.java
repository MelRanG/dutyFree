package com.asianaidt.dutyfree.domain.product.repository;

import com.asianaidt.dutyfree.domain.product.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long categoryNum);
}
