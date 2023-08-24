package com.asianaidt.dutyfree.domain.purchase.repository;

import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseLogRepository extends JpaRepository<PurchaseLog, Long> {
}
