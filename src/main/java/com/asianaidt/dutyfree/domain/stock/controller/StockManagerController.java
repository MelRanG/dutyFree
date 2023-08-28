package com.asianaidt.dutyfree.domain.stock.controller;

import com.asianaidt.dutyfree.domain.purchase.dto.BrandSalesDto;
import com.asianaidt.dutyfree.domain.purchase.dto.DailySalesDto;
import com.asianaidt.dutyfree.domain.purchase.dto.MonthlySalesDto;
import com.asianaidt.dutyfree.domain.purchase.service.PurchaseService;
import com.asianaidt.dutyfree.domain.stock.domain.StockStatus;
import com.asianaidt.dutyfree.domain.stock.dto.StockManagerRequestDto;
import com.asianaidt.dutyfree.domain.stock.service.StockManagerService;
import com.asianaidt.dutyfree.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class StockManagerController {
    private final StockManagerService stockManagerService;
    private final PurchaseService purchaseService;
    private final StockService stockService;

    @GetMapping()
    public String admin(Model model, Pageable pageable){

        model.addAttribute("allStock", stockService.getProductStockList(pageable));

        model.addAttribute("progress",stockManagerService.getStockManagerProgress(pageable)); // 프로그레스

//f

        return "Admin";
    }

    @GetMapping("/history")
    public String adminStock(Model model, Pageable pageable){

        model.addAttribute("stockCompleted",stockManagerService.getStockManagerCompleted(pageable));

        return "AdminHis";
    }






    /*
    INPUT
    stockId : Long,
    quantity : int,
    memberId : String
     */
    @PostMapping("/stock/insert")
    public String insertStock(@RequestBody StockManagerRequestDto dto, Model model){
        try{
            stockManagerService.insertStock(dto);
            model.addAttribute("message", "발주가 신청됐습니다.");
            System.out.println("dto = " + dto.toString());
            return "Admin";
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PatchMapping("/stock/status/{id}")
    public String updateStockStatus(@PathVariable Long id, Model model){
        try{
            stockManagerService.updateStockStatus(id);
            model.addAttribute("message", "발주가 정상적으로 완료됐습니다.");
            return "redirect:/Admin";
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }


    /*
    OUTPUT
    List로 리턴 이름 : stockManagerList
    id : Long
    regDate : String
    quantity: int
    memberId : int
    status : String
    Stock : 객체
     */

    @GetMapping("/stock/test")
//    public String getStockManagerList(Pageable pageable, Model model, @RequestParam StockStatus status){
    public ResponseEntity<?> getStockManagerListTest(Pageable pageable, Model model, @RequestParam StockStatus status){

        model.addAttribute("stockManagerList",stockManagerService.getStockManagerList(pageable, status));

        return ResponseEntity.ok(stockManagerService.getStockManagerList(pageable,status));
    }
    @GetMapping("/stock/status/test")
//    public String getStockManagerList(Pageable pageable, Model model, @RequestParam StockStatus status){
    public ResponseEntity<?> getStatus(Pageable pageable, Model model){

//        model.addAttribute("stockManagerList",stockManagerService.getStockManagerList(pageable, status));

        return ResponseEntity.ok(stockManagerService.getStockManagerProgress(pageable));
    }
    /*
    INPUT
    pageable
    OUTPUT
    String name;
    String brand;
    String category;
    int price;
    int quantity;
     */

    @GetMapping("/product/list")
    public String getProductStockList(Model model, Pageable pageable){
        model.addAttribute("allStock", stockService.getProductStockList(pageable));
        return "AdminStock";
    }
    @GetMapping("/product/list/test")
    public ResponseEntity<?> getProductStockListTest(Model model, Pageable pageable){
        model.addAttribute(stockService.getProductStockList(pageable));
        return ResponseEntity.ok(stockService.getProductStockList(pageable));
    }
    @GetMapping("/sales/total")
    @ResponseBody
    public Map<String, Integer> calculateTotalAmount() {
        Map<String, Integer> response = new HashMap<>();
        response.put("totalAmount", purchaseService.calculateTotalAmount());
        return response;
    }
/*
    OUTPUT
    int year;
    int month;
    long price;
    long totalSales;
 */
    @GetMapping("/sales/month")
    @ResponseBody
    public Map<String, List<MonthlySalesDto>> calculateMonthlySales(){
        Map<String, List<MonthlySalesDto>> response = new HashMap<>();
        response.put("monthlySales", purchaseService.calculateMonthlySales());
        return response;
    }

    /*
    OUTPUT
    int brand
    int totalSales
     */
    @GetMapping("/sales/brand")
    @ResponseBody
    public Map<String, List<BrandSalesDto>> calculateBrandSales(){
        Map<String, List<BrandSalesDto>> response = new HashMap<>();
        response.put("brandSales", purchaseService.calculateBrandSales());
        return response;
    }
    /*
    --year, month input 필요
    OUTPUT
    int day
    int totalSales
     */
    @GetMapping("/sales/day")
    @ResponseBody
    public Map<String, List<DailySalesDto>> calculateDaySales(@RequestParam int year, @RequestParam int month){
        Map<String, List<DailySalesDto>> response = new HashMap<>();
        response.put("dailySales", purchaseService.calculateDailySalesForMonth(year, month));
        return response;
    }
}
