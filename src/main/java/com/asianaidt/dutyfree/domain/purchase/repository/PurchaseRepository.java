package com.asianaidt.dutyfree.domain.purchase.repository;


import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("select p from Purchase p join fetch p.purchaseDetails where p.member.id=:id") // (1)
    List<Purchase> findAllByMemberId(String id);

    Optional<Purchase> findByMemberAndId(Member member, Long id);
}
