package com.asianaidt.dutyfree.domain.stock.repository;

import com.asianaidt.dutyfree.domain.stock.domain.StockManager;
import com.asianaidt.dutyfree.domain.stock.domain.StockStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockManagerRepository extends JpaRepository<StockManager, Long> {

    Page<StockManager>  findAllByStatus(Pageable pageable, StockStatus status);
}
