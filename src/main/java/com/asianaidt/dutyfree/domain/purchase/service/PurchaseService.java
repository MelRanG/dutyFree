package com.asianaidt.dutyfree.domain.purchase.service;


import com.asianaidt.dutyfree.domain.member.domain.Member;
import com.asianaidt.dutyfree.domain.member.dto.MemberResponseDto;
import com.asianaidt.dutyfree.domain.member.repository.MemberRepository;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import com.asianaidt.dutyfree.domain.purchase.domain.Purchase;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseDetail;
import com.asianaidt.dutyfree.domain.purchase.domain.PurchaseLog;
import com.asianaidt.dutyfree.domain.purchase.dto.*;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseDetailRepository;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseLogRepository;
import com.asianaidt.dutyfree.domain.purchase.repository.PurchaseRepository;
import com.asianaidt.dutyfree.domain.stock.domain.Stock;
import com.asianaidt.dutyfree.domain.stock.facade.OptimisticLockStockFacade;
import com.asianaidt.dutyfree.domain.stock.repository.StockRepository;
import com.asianaidt.dutyfree.global.error.StandardException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseService {
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final StockRepository stockRepository;
    private final PurchaseLogRepository purchaseLogRepository;
    private final OptimisticLockStockFacade stockService;
    private final MemberRepository memberRepository;

    public List<Purchase> getPurchaseList(String memberId) {
        return purchaseRepository.findByMemberId(memberId).orElse(new ArrayList<>());
    }

    public List<PurchaseDetail> getPurchase(Long purchaseId) {
        Optional<List<PurchaseDetail>> details = purchaseDetailRepository.findAllByPurchaseId(purchaseId);
        if(details.isEmpty()) {
            throw new StandardException("구매 정보를 찾을 수 없습니다.");
        }

        return details.get();
    }

    public void addPurchaseDetails(Purchase purchase, List<CartProductDto> detailDtoList) throws InterruptedException {
        int totalAmount = 0;

        for(CartProductDto detailDto : detailDtoList) {
            long productId = detailDto.getProductId();
            Optional<Product> product = productRepository.findById(productId);
            Optional<Stock> stock = stockRepository.findByProductId(productId);
            if(product.isPresent() && stock.isPresent()) {
                // 재고 확인
                stockService.decrease(stock.get().getId(), detailDto.getQuantity());

                PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                        .purchase(purchase)
                        .quantity(detailDto.getQuantity())
                        .product(product.get())
                        .build();

                // 총 수량 합산
                totalAmount += detailDto.getQuantity();

                purchaseDetailRepository.save(purchaseDetail);

                PurchaseLog purchaseLog = PurchaseLog.builder()
                        .regDate(LocalDateTime.now())
                        .price(product.get().getPrice() * detailDto.getQuantity())
                        .brand(product.get().getBrand())
                        .productName(product.get().getName())
                        .productId(product.get().getId())
                        .quantity(detailDto.getQuantity())
                        .category(product.get().getCategory())
                        .build();

                purchaseLogRepository.save(purchaseLog);

            }
        }

        purchase.setTotalAmount(totalAmount);
        purchaseRepository.save(purchase);
    }

    @Transactional
    public void purchaseMany(MemberResponseDto memberResponseDto, List<CartProductDto> purchaseDto) throws InterruptedException {
        Optional<Member> member = memberRepository.findById(memberResponseDto.getId());

        if(member.isPresent()) {
            Purchase purchase = Purchase.builder()
                    .regDate(LocalDateTime.now())
                    .member(member.get())
                    .build();

            purchaseRepository.save(purchase);

            addPurchaseDetails(purchase, purchaseDto);
        }


    }

    @Transactional
    public void purchase(Member member, Long productId, int quantity) throws InterruptedException {
        Optional<Product> product = productRepository.findById(productId);
        Optional<Stock> stock = stockRepository.findByProductId(productId);

        if(product.isPresent() && stock.isPresent()) {
            if(stock.get().getQuantity() < quantity){
                throw new RuntimeException("재고는 0을 초과할 수 없습니다.");
            }
            Purchase purchase = Purchase.builder()
                    .regDate(LocalDateTime.now())
                    .member(member)
                    .build();

            purchaseRepository.save(purchase);

            log.info("stockId= {}", stock.get().getId());

                stockService.decrease(stock.get().getId(), quantity);

            PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                    .purchase(purchase)
                    .quantity(quantity)
                    .product(product.get())
                    .build();

            purchaseDetailRepository.save(purchaseDetail);

            PurchaseLog purchaseLog = PurchaseLog.builder()
                    .regDate(LocalDateTime.now())
                    .price(product.get().getPrice() * quantity)
                    .brand(product.get().getBrand())
                    .productName(product.get().getName())
                    .productId(product.get().getId())
                    .quantity(quantity)
                    .category(product.get().getCategory())
                    .build();

            purchaseLogRepository.save(purchaseLog);

        }
    }

    public int calculateTotalAmount(){
        return purchaseLogRepository.findAll().stream()
                .mapToInt(PurchaseLog::getPrice)
                .sum();
    }

    public List<MonthlySalesDto> calculateMonthlySales(){
        return purchaseLogRepository.findMonthlySales(PageRequest.of(0,12));
    }
    public List<BrandSalesDto> calculateBrandSales(){
        return purchaseLogRepository.findBrandSales(PageRequest.of(0,6));
    }
    public List<CategorySalesDto> calculateCategorySales(){
        return purchaseLogRepository.findCategorySales(PageRequest.of(0,6));
    }
    public List<DailySalesDto> calculateDailySalesForMonth(int year, int month){
        int daysInMonth = YearMonth.of(year,month).lengthOfMonth();
        List<DailySalesDto> allDays = new ArrayList<>();
        for (int day = 1; day <= daysInMonth; day++) {
            allDays.add(new DailySalesDtoImpl(LocalDate.of(year, month, day), 0));
        }
        // 2단계: DB에서 실제 판매 데이터 가져오기
        List<DailySalesDto> actualData = purchaseLogRepository.findDailySalesForMonth(year, month);

        // 실제 판매 데이터로 더미 데이터 업데이트
        for (DailySalesDto data : actualData) {
            int index = data.getDay().getDayOfMonth() - 1;
            allDays.set(index, data);
        }
        return allDays;
    }

}
