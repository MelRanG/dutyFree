package com.asianaidt.dutyfree.domain.purchase.repository;


import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
