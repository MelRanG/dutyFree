package com.asianaidt.dutyfree.domain.stock.repository;

import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.dto.ProductStockDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductId(Long productId);
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithOptimisticLock(Long id);

    @Query("select new com.asianaidt.dutyfree.domain.stock.dto.ProductStockDto(p.id, p.name, p.brand, c.name, p.price , s.quantity) " +
            "from Stock s " +
            "left join s.product p " +
            "left join p.category c " +
            "order by s.quantity")
    Page<ProductStockDto> findByProductStock(Pageable pageable);
}
