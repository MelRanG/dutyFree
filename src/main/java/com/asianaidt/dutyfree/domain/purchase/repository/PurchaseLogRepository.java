package com.asianaidt.dutyfree.domain.purchase.repository;

import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseLog;
import com.asianaidt.dutyfree.domain.stock.dto.BrandSalesDto;
import com.asianaidt.dutyfree.domain.stock.dto.MonthlySalesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseLogRepository extends JpaRepository<PurchaseLog, Long> {
    @Query("SELECT new com.asianaidt.dutyfree.domain.stock.dto.MonthlySalesDto(FUNCTION('YEAR', p.regDate), FUNCTION('MONTH', p.regDate), SUM(p.price), SUM(p.quantity)) " +
            "FROM PurchaseLog p " +
            "GROUP BY FUNCTION('YEAR', p.regDate), FUNCTION('MONTH', p.regDate) " +
            "ORDER BY FUNCTION('YEAR', p.regDate) DESC, FUNCTION('MONTH', p.regDate) DESC")
    List<MonthlySalesDto> findMonthlySales();

    @Query("SELECT p.brand as brand, SUM(p.price * p.quantity) as totalSales from PurchaseLog p " +
            "GROUP BY p.brand")
    List<BrandSalesDto> findBrandSales();
}
