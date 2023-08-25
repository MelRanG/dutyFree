package com.asianaidt.dutyfree.domain.purchase.repository;

import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {
    Optional<List<PurchaseDetail>> findAllByPurchaseId(Long productId);
}
