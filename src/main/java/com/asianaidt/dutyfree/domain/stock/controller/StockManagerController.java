package com.asianaidt.dutyfree.domain.stock.controller;

import com.asianaidt.dutyfree.domain.stock.dto.StockManagerRequestDto;
import com.asianaidt.dutyfree.domain.stock.service.StockManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class StockManagerController {
    private final StockManagerService stockManagerService;

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
            return "redirect:/Admin";
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
