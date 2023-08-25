package com.asianaidt.dutyfree.domain.stock.repository;

import com.asianaidt.dutyfree.domain.stock.domain.StockManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockManagerRepository extends JpaRepository<StockManager, Long> {
}
